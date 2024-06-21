<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

<section>
    <%@ include file="sub_image_menu.jsp" %>
    <article>
        <form method="post" action="login" name="loginForm">
            <h2>LogIn</h2>
            <div class="field">
                <label>User ID</label><input name="userid" type="text" />
            </div>
            <div class="field">
                <label>Password</label><input name="pwd" type="password" >
            </div>
            <div class="btn">
                <input type="submit" value="LOGIN">
                <input type="button" value="JOIN"  onclick="" >
                <input type="button" value="FIND ID"  onclick="" >
            </div>
            <div class="btn">
                <input type="button" value="KaKao" style="background:yellow; color:black;" onClick="location.href='kakaostart'">
                <input type="button" value="Naver" style="background:green" onclick="" >
                <input type="button" value="Google" style="background:red" onclick="" >
            </div>
            <div style="font-size:80%; font-weight:bold">${message} </div>
        </form>
    </article>
    </div><!-- nav 와 article 의 부모 끝 -->
</section>




<%@ include file="../include/footer.jsp" %>
