$(function(){
    $('.hmenu').click(function(){
        $('.hmenu div').toggleClass('active');
        $('.gnb').toggle(300, function(){});
    });
});

function go_next(){
    if( document.contractFrm.okon[1].checked == true ){
        alert('회원 약관에 동의 하셔야 회원으로 가입이 가능합니다');
    }else{
        document.contractFrm.submit();
    }
}


function idcheck(){
    if( document.joinForm.userid.value==""){
        alert("아이디를 입력하고 중복체크를 진행하세요" );
        documnet.joinForm.userid.focus();
        return;
    }
    var url = "idcheckForm?userid=" + document.joinForm.userid.value;
    var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";

    window.open(url, "idcheck", opt);
}

function idok( userid ){
    opener.joinForm.userid.value = userid;
    opener.joinForm.reid.value = userid;
    self.close();
}

