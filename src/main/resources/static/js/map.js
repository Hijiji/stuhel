
    <!--지도-->
    var userLng,userLat, mapContainer, map, infowindow,
        ps = new kakao.maps.services.Places(); // 장소 검색 객체를 생성;
    <!--var keyword = document.getElementById('keyword').value;-->
    var markers = [];
	navigator.geolocation.getCurrentPosition(getLocWeather, showErrorMsg);

    function getLocWeather(position){ //현재 위치
	    userLat = position.coords.latitude; //위도
	    userLng = position.coords.longitude;//경도

        mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(userLat, userLng), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
        map = new kakao.maps.Map(mapContainer, mapOption); //지도생성
        infowindow = new kakao.maps.InfoWindow({zIndex:1}); // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성
        searchPlaces("맛집");
    }

    function showErrorMsg(error){ //위치찾기 오류발생
	    switch(error.code){
		    case error.PERMISSION_DENIED: alert("사용자가 사용 요청을 거부했습니다."); break;
		    case error.POSITION_UNAVAILABLE: alert("가져온 위치 정보를 사용할 수 없습니다."); break;
		    case error.TIMEOUT: alert("요청 허용 시간을 초과했습니다."); break;
		    case error.UNKNOWN_ERROR: alert("알 수 없는 오류가 발생했습니다."); break;
	    }
    }

    function searchPlaces(keyword) {// 키워드 검색 요청
        if (!keyword.replace(/^\s+|\s+$/g, '')) {
            alert('키워드를 입력해주세요');
            return false;
        }
        ps.keywordSearch(keyword, placesSearchCB,{x:userLng , y:userLat}); // 장소검색 객체를 통해 키워드로 장소검색을 요청
    }

    function placesSearchCB(data, status, pagination) { // 장소검색이 완료됐을 때 호출되는 콜백함수
      if (status === kakao.maps.services.Status.OK) {
        displayPlaces(data);            // 정상적으로 검색이 완료됐으면 검색 목록과 마커를 표출
        displayPagination(pagination);  // 페이지 번호를 표출
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
            return;
        } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
            return;
        }
    }

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {

        var listEl = document.getElementById('placesList'),
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = '';
        removeAllChildNods(listEl); // 검색 결과 목록에 추가된 항목들을 제거
        removeMarker(); // 지도에 표시되고 있는 마커를 제거

        for ( var i=0; i<places.length; i++ ) {

            var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),        // 마커를 생성하고 지도에 표시
                marker = addMarker(placePosition, i),
                itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성
                bounds.extend(placePosition); // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가

            // 마커와 검색결과 항목에 mouseover 했을때 해당 장소에 인포윈도우에 장소명을 표시합니다 mouseout 했을 때는 인포윈도우를 닫습니다
            (function(marker, title) {
                kakao.maps.event.addListener(marker, 'mouseover', function() {
                    displayInfowindow(marker, title);
                });
                kakao.maps.event.addListener(marker, 'mouseout', function() {
                  infowindow.close();
                });
                itemEl.onmouseover =  function () {
                  displayInfowindow(marker, title);
                };
                itemEl.onmouseout =  function () {
                    infowindow.close();
                };
            })(marker, places[i].place_name);

            fragment.appendChild(itemEl);
        }

        listEl.appendChild(fragment); // 검색결과 항목들을 검색결과 목록 Element에 추가
        menuEl.scrollTop = 0;

        map.setBounds(bounds);    // 검색된 장소 위치를 기준으로 지도 범위를 재설정
    }

    function getListItem(index, places) {// 검색결과 항목을 Element로 반환하는 함수

        var el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                    '<div class="info">' +
                    '   <h5>' + places.place_name + '</h5>';

        if (places.road_address_name) {
            itemStr += '    <span>' + places.road_address_name + '</span>' +
                        '   <span class="jibun gray">' +  places.address_name  + '</span>';
        } else {
            itemStr += '    <span>' +  places.address_name  + '</span>';
        }

        itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                    '</div>';

        el.innerHTML = itemStr;
        el.className = 'item';

        return el;
    }


    function addMarker(position, idx, title) { // 마커를 생성하고 지도 위에 마커를 표시하는 함수
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지 사용
            imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
            imgOptions =  {
                spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage
            });

        marker.setMap(map); // 지도 위에 마커를 표출
        markers.push(marker);  // 배열에 생성된 마커를 추가
        return marker;
    }

    function removeMarker() {// 지도 위에 표시되고 있는 마커를 모두 제거
        for ( var i = 0; i < markers.length; i++ ) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    function displayPagination(pagination) {// 검색결과 목록 하단에 페이지번호를 표시는 함수
        var paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment(),
            i;

        while (paginationEl.hasChildNodes()) {     // 기존에 추가된 페이지번호를 삭제
            paginationEl.removeChild (paginationEl.lastChild);
        }

        for (i=1; i<=pagination.last; i++) {
            var el = document.createElement('a');
            el.href = "#";
            el.innerHTML = i;

            if (i===pagination.current) {
                el.className = 'on';
            } else {
                el.onclick = (function(i) {
                    return function() {
                        pagination.gotoPage(i);
                    }
                })(i);
            }

            fragment.appendChild(el);
        }
        paginationEl.appendChild(fragment);
    }

    function displayInfowindow(marker, title) {// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수
        var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

        infowindow.setContent(content);// 인포윈도우에 장소명을 표시
        infowindow.open(map, marker);
    }

    // 검색결과 목록의 자식 Element를 제거하는 함수
    function removeAllChildNods(el) {
        while (el.hasChildNodes()) {
            el.removeChild (el.lastChild);
        }
    }
