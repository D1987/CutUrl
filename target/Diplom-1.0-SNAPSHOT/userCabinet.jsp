<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link href="css/userCabinet.css" type="text/css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script charset="windows-1251" src="js/index.js"></script>
</head>
<body>                                                                      <!--HEADER-->
<div class="main">
    <c:set var="login" value="${param.login}" scope="session"/>
    <c:set var="idU" value="${param.idU}" scope="session"/>
    <c:set var="id" value="${idU}" scope="request"/>
    <c:set var="tag" value="${param.tag}" scope="session"/>
    <c:set var="tagsU" value="${tags}" scope="page"/>
    <c:set var="i" value="0" scope="page"/>
    <c:set var="where" value="false" scope="session"/>

    <!--menu-->
    <div class="navigation">
        <img src="images/Scissors-icon.png" id="sci">
        <table id="name">
            <tr>
                <td id="n2"><img src="images/user.png" id="pic2">${login}</td>
            </tr>
        </table>
        <div class="menu">
            <ul>
                <li>
                    <form method="post" action="sokr.jsp">
                        <input type="hidden" name="idU" value="${idU}">
                        <input type="hidden" name="login" value="${login}">
                        <button id="butRedirSokr">На главную</button>
                    </form>
                </li>
                <li><a href="#" id="blodOut">Разлогиниться</a></li>
            </ul>
        </div>
    </div>

                                                    <!-------------CONTENT------------->
    <div class="content">
        <!-- razloginitsya-->
        <div id="logOut">
            <form name="Form" action="sokr.jsp" >
                <p id="textLogOut">Уже уходите?!</p>
                <button id="bYes">Да</button>
            </form>
            <button id="bNo">Нет</button>
        </div>

        <!-------------update person date------------->
        <div id="updateE">
            <img src="images/ClosePopup.png" id="bCloseU" onclick="closeButtonU('updateE','update')">
            <div id="update">
                <p id="povorot" onclick="showConUpdate('updateE','update')">Редактировать</p>
                <p id="povorot1" onclick="showConUpdate('updateE','update')">личные данные</p>
            </div>
            <form name="Form" class="updateU">
                <ul>
                    <li><input type="text" id="loginNew" value="" placeholder="Новый логин" class="vvod"></li>
                    <p id="erNewLog"></p>
                    <li><input type="password" id="passwordNew" value="" placeholder="Новый пароль" class="vvod"></li>
                    <p id="erNewPass"></p>
                    <li><input type="password" id="passwordNewRepeate" value="" placeholder="Повторить пароль" class="vvod"></li>
                    <p id="erNewPassRep"></p>
                    <li><input type="hidden" id="loginOld" value="${login}"></li>
                    <li><input type="hidden" id="idU" value="${idU}"></li>
                </ul>
                <button type="button" id="bUpUser" onclick="updatePersonData()">Изменить</button>
            </form>

            <!-------------delete akkaunt------------->
            <img src="images/user-blue-delete.png" id="iconDeleUser"><button class="deleteU" id="bDeleteUser" onclick="deleteAkkaunt('DeleteU')">Удалить аккаунт</button>
            <form name="Form2" method="post" action="http://localhost:81/UserDelete" id="DeleteU">
                    <p id="textDelAkk">Вы действительно
                    хотите удалить аккаунт?
                    Все Ваши ссылки будут удалены.</p>
                    <input type="hidden" name="idU" value="${idU}"/>
                    <input type="hidden" name="login" value="${login}"/>
                    <button  id="bYDeleteU">Да</button>
                    <button type="button" id="bNDeleteU" onclick="bDeleteAkkauntNo('DeleteU')">Нет</button>
            </form>
        </div>

        <!-------------update ssylki------------->
        <div id="updateRef">
            <img src="images/ClosePopup.png" id="bCloseR">
            <form id="Form3" class="updateR">
                <ul>
                    <li><input type="text" id="description" placeholder="Описание" value="" class="vvod"></li>
                    <li><input type="text" id="tagU" placeholder="Тег" value="" class="vvod"></li>
                    <p id="erTagD"></p>
                    <li><input type="hidden"  value="" id="val"></li>
                    <li><input type="hidden" id="loginUpdRef" value="${login}"></li>
                </ul>
                <button type="button" id="bUpdateRef" onclick="updateDescAndTags()">Изменить</button>
            </form>
        </div>

        <!-- *************** udalenie ssylki****************-->
        <div id="winDel">
            <div class="delR">
                <button class="butR2" onclick="hideDelRef('winDel')">Нет</button>
                <p class="parDelR">Удалить ссылку?</p>
                <form >
                    <input type="hidden" name="id" value="" id="del">
                    <input type="hidden" name="login" value="${login}">
                    <input type="hidden" name="idU" value="${idU}" id="idUs">
                    <button type="button" class="butR1" onclick="deleteRef()">Да</button>
                </form>
            </div>
        </div>

        <!-------------sokrashenie ssylki------------->
        <p id="textCut">Сократите ссылку</p>

        <form name="Form4" class="inputR">
            <input type="text" name="ssylka" value="" placeholder="Ссылка" id="ref"><br>
            <p id="erSylka"></p>
            <input type="text" name="description" value="" placeholder="Комментарий" id="desc"><br>
            <p id="erDescription"></p>
            <input type="text" name="tag" placeholder="Тег" id="tag"><br>
            <p id="ertag"></p>
            <input type="hidden" id="login" value="${login}"><br>
            <button type="button" id="bSokr" class="cbutton cbutton--effect-novak" onclick="cutssulk()">Сократить</button>
        </form>

        <!-------------vyvod ssylok------------->
        <div class="side" id="reloadR">
                <jsp:useBean id="personData" class="servlets.UserData"/>
            <c:catch>
                <c:set var="pData" value="${personData.vuborkaPersonData(pageContext.request)}"/>
                <c:forEach var="entry" items="${pData}">
            <ul class="menu1">
                <li class="menu_list"><a href="http://localhost:81/ReferRedirect?id=${entry.key}" target="_blank">${entry.value.getCut_ref()}</a>
                    <ul class="menuRDrop">
                        <li>
                            <table class="t1">
                                <c:if test="${not empty entry.value.getDescription()}">
                                <tr>
                                    <td class="col">Описание: </td>
                                    <td class="col2">  <div class="wrapDescr">${entry.value.getDescription()} </div></td>
                                </tr>
                                </c:if>
                                <c:if test="${not empty entry.value.getTag()}">
                                <tr>
                                    <td class="col">Тег: </td>
                                    <td class="col2">
                                        <form name="RedUserCab" method="post" action="http://localhost:81/UserTags">
                                            <input type="hidden" name="loginTags" value="${login}">
                                            <input type="hidden" name="login" value="${login}">
                                            <input type="hidden" name="tag" value="${entry.value.getTag()}">
                                            <input type="hidden" name="where" value="${where}">
                                            <button id="bTag">#${entry.value.getTag()}</button>
                                        </form>
                                       </td>
                                </tr>
                                </c:if>
                                <tr>
                                    <td class="col">Кликов: </td>
                                    <td class="col2">${entry.value.getCount()}</td>
                                </tr>
                                <tr>
                                    <td class="col1">QR</td>
                                    <td class="col2"><img alt="QR code" src="ReferQR?id=${entry.key}"> </td>
                                </tr>
                            </table>
                            <table class="tabl">
                                <tr>
                                    <td><img src="images/up.png" onclick="showConUpdateRef('val','${entry.key}')" id="upRef"></td>
                                    <td><img src="images/t.png" onclick="showDelRef('winDel','del','${entry.key}')" id="delref"></td>
                                </tr>
                            </table>
                        </li>
                    </ul>
                </li>
            </ul>
                </c:forEach>
            </c:catch>
        </div>

        <!--spisok tegov-->
        <div id="tags">
            <div id="clo">
                <c:if test="${not empty tagsU}">
                <script> runIt1() </script >
                <form name="clean" method="post" action="http://localhost:81/UserTags">
                    <input type="hidden" name="loginTags" value="${login}">
                    <input type="hidden" name="where" value="${where}">
                    <img src="images/standardbutton-close-32.png" id="bCTags">
                </form>
                </c:if>
               </div>
            <table class="spisokTags">
            <c:forEach var="entry" items="${tagsU}">
                <c:choose>
                    <c:when test="${i%2!=0}">
                        <p style="display: none"> ${i=i+1}</p>
                        <tr class="spisokTagsColor1">
                            <td class="col1">Пользователь: </td>
                            <td class="numRef col3">${entry.value.getLogin()}</td>
                        </tr>
                        <tr class="spisokTagsColor1">
                            <td class="col1">Ссылка: </td>
                            <td class="col3"><a href="http://localhost:81/ReferRedirect?id=${entry.key.getId()}" target="_blank" >${entry.value.getCut_ref()}</a></td>
                        </tr>
                        <tr class="spisokTagsColor1">
                            <td class="col1">Тег: </td>
                            <td class="numRef col3">#${tag}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <p style="display: none"> ${i=i+1}</p>
                        <tr class="spisokTagsColor2">
                            <td class="col1">Пользователь: </td>
                            <td class="numRef col3">${entry.value.getLogin()}</td>
                        </tr>
                        <tr class="spisokTagsColor2">
                            <td class="col1">Ссылка: </td>
                            <td class="col3"><a href="http://localhost:81/ReferRedirect?id=${entry.key.getId()}" target="_blank">${entry.value.getCut_ref()}</a></td>
                        </tr>
                        <tr class="spisokTagsColor2">
                            <td class="col1">Тег: </td>
                            <td class="numRef col3">#${tag}</td>
                        </tr>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
                </table>
            </div>

    </div>
                                                                        <!------PODVAL-------->
    <div class="podval">
        <table class="tablePodval">
            <tr>
                <td><img src="images/logo_mts.png" id="mts"></td>
                <td><span>+375 33 3125695</span></td>
                <td><img src="images/logo_skype.png" id="skype"></td>
                <td><span>Legenda19873</span></td>
                <td><img src="images/location.png" id="location"></td>
                <td><span>г. Гродно, ул. В.Ольшанка 3А, кв. 17</span></td>
                <td><img src="images/phone_number_icon.png" id="phone"></td>
                <td><span>(0152)671236</span></td>
            </tr>
        </table>
        <p>© 2015-2016 Дмитрий Зимин. Все права защищены</p>
    </div>
</div>
</body>
</html>
