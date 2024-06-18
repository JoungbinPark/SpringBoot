function idCheck(){
    if(document.join.userid.value==""){
        alert('아이디를 입력하여 주십시오.');
        document.join.userid.focus();
        return;
    }
    var k = document.join.userid.value;
    var opt = "toolbar=no, menubar=no, resizable=no, width=450, height=200";
    window.open(  "idcheck?userid="+ k ,'id check', opt);
}