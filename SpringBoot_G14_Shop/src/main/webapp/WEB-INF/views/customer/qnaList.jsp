<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<section>

    <%@ include file="sub_image_menu.jsp" %>
    <article>
        <form>
            <h2> Q & A List </h2>
            <input type="button" onClick="location.href='qnaWriteForm'" value="글 작성"/>
            <div class="tb">
                <div class="row">
                    <div class="coltitle">번호</div>
                    <div class="coltitle" style="flex:3;">제목</div>
                    <div class="coltitle">작성일</div>
                    <div class="coltitle">답변여부</div>
                </div>
                <c:forEach items="${qnaList}"  var="qnaVO">
                    <div class="row">
                        <div class="col">${qnaVO.qseq}</div>
                        <div class="col" style="flex:3; display:flex; justify-content: flex-start">
                            <c:choose>
                                <c:when test="${qnaVO.security=='Y'}">
                                    <div onClick="qnaViewWithPass('${qnaVO.pass}', '${qnaVO.qseq}')">
                                            ${qnaVO.subject}&nbsp;<img src="/images/key.png" style="width:20px; vertical-align: middle;"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <a href="qnaView?qseq=${qnaVO.qseq}">${qnaVO.subject}</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col"><fmt:formatDate value="${qnaVO.indate}" type="date"/></div>
                        <div class="col">
                            <c:choose>
                                <c:when test="${empty qnaVO.reply}" >NO</c:when>
                                <c:otherwise>YES</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>

                <%--<c:url var="action" value="qnaList" />
                <div class="row">  <!-- 페이지의 시작 -->
                    <div class="coltitle" style="font-size:120%; font-weight:bold;">

                        <c:if test="${paging.prev}">
                            <a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
                        </c:if>
                        <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
                            <c:if test="${index==paging.page}">
                                <span style="color:red">${index}&nbsp;</span>
                            </c:if>
                            <c:if test="${index!=paging.page}">
                                <a href="${action}&page=${index}">${index}&nbsp;</a>
                            </c:if>
                        </c:forEach>
                        <c:if test="${paging.next}">
                            &nbsp;<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
                        </c:if>

                    </div>
                </div><!-- 페이지의 끝 -->
                <div class="row">
                    <div class="coltitle">
                        <div class="btn">
                            <input type="button" value="질문하기"
                                   onClick="location.href='writeQnaForm'"/>
                        </div>
                    </div>
                </div>--%>
            </div>
        </form>
    </article>

    </div>

</section>
<%@ include file="../include/footer.jsp" %>
