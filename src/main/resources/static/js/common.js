    /*session확인-로그인확인*/
    function sessionCheck(){
        let xhr = new XMLHttpRequest();
        xhr.open('Get', "/session/sessionCheck"
            ,true);
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let sessionStatus = xhr.responseText;
                if(sessionStatus==null||sessionStatus==''){
                    location.href="/index.html";
                }
            }
        }
    };
    /*로그아웃*/
    function logoutButton(){
        let xhr = new XMLHttpRequest();
        xhr.open('DELETE', "/session/logout"
            ,true);
        xhr.setRequestHeader('Accept', 'application/json');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                location.href="/index.html";
            }
        }
    }

/*서버가기전 확인하는 alert*/
function checkAlert(title, text,buttons){
    if(buttons==null||buttons==''){buttons=["취소","확인"];}
    //if(btnColor==null||btnColor==''){btnColor='tomato';}
    swal({
        title : title
        , text : text
        , buttons : buttons
    }).then(function(result) {
        return result;
    });
}
