function go_cart(){
    if( document.formm.quantity.value == ""){
        alert("수량을 입력하세요");
        document.formm.quantity.focus();
    }else{
        document.formm.action = 'cartInsert';
        document.formm.submit();
    }
}

function go_cart_delete(){
    var count = 0;
    if( document.cartFrm.cseq.length == undefined ){
        if( document.cartFrm.cseq.checked==true){
            count++;
        }
    }else{
        for(var i=0; i<document.cartFrm.cseq.length; i++){
            if( document.cartFrm.cseq[i].checked== true){
                count++;
            }
        }
    }

    if( count == 0){
        alert("삭제할 항목을 선택하세요");
    }else{
        var ans = confirm("선택한 항목을 삭제할까요?");
        if( ans ){
            document.cartFrm.action = "cartDelete";
            document.cartFrm.submit();
        }
    }
}



function go_order_insert(){
    var count = 0;
    if( document.cartFrm.cseq.length == undefined ){
        if( document.cartFrm.cseq.checked==true) {
            count++;
        }
    }else{
        for(var i=0; i<document.cartFrm.cseq.length; i++){
            if( document.cartFrm.cseq[i].checked== true){
                count++;
            }
        }
    }
    if( count == 0){
        alert("주문할 항목을 선택하세요");
    }else{
        var ans = confirm("선택한 항목을 주문할까요?");
        if( ans ){
            document.cartFrm.action = "orderInsert";
            document.cartFrm.submit();
        }
    }

}


function go_order(){
    var ans = confirm("현재 상품을 주문할까요?");
    if( ans ){
        document.formm.action = "orderInsertOne";
        document.formm.submit();
    }
}





function go_updateMember(){
    if( document.joinForm.pwd.value == "") {
        alert("비밀번호를 입력해 주세요.");
        document.joinForm.pwd.focus();
    } else if( document.joinForm.pwd.value != document.joinForm.pwdCheck.value) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        document.joinForm.pwd.focus();
    } else if( document.joinForm.name.value == "") {
        alert("이름을 입력해 주세요.");
        document.joinForm.name.focus();
    } else if( document.joinForm.phone.value == "") {
        alert("전화번호를 입력해 주세요.");
        document.joinForm.phone.focus();
    }else if( document.joinForm.email.value == "") {
        alert("이메일을 입력해 주세요.");
        document.joinForm.email.focus();
    } else{
        document.joinForm.submit();
    }
}



function withdrawal(){
    var ans = confirm("정말로 탈퇴하시겠습니까?")
    if( ans ){
        location.href="shop.do?command=deleteMember";
    }
}






