<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Сервис сокращения ссылок</title>
    <link href="css/index.css" type="text/css" rel="stylesheet"/>
    <script src="js/jquery.js"></script>
    <script charset="windows-1251" src="js/index.js"></script>
</head>
<body>
<c:set var="login" value="${param.login}" scope="session"/>
<c:set var="idU" value="${param.idU}" scope="session"/>
<c:set var="tag" value="${param.tag}" scope="session"/>
<c:set var="tagU" value="${tags}" scope="page"/>
<c:set var="i" value="0" scope="page"/>
<c:set var="where" value="true" scope="session"/>
                                                                                    <!--HEADER-->
    <!--menu-->
<div class="main">
    <div class="navigation">
     <img src="images/Scissors-icon.png" id="sci">
        <div class="menu">
            <ul>
                <c:choose>
                    <c:when test="${not empty login}">
                        <table class="name">
                            <tr>
                                <td id="name"><img src="images/user.png" id="pic1">${login}</td>
                            </tr>
                        </table>
                        <li>
                            <form name="RedUserCab" method="post" action="userCabinet.jsp">
                                <input type="hidden" name="idU" value="${idU}">
                                <input type="hidden" name="login" value="${login}">
                                <button  id="butRedirUC">Личный кабинет</button>
                            </form>
                        </li>
                    </c:when>
                        <c:otherwise>
                        <li><a href="#popup" onclick="clearP()" >Регистрация</a></li>
                        <li><a href="#popup1" onclick="clearP()">Войти</a></li>
                        </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
    <!--registraciya popup-->
    <div id="popup">
        <div class="window">
            <a href="#"> <img src="images/CrossClose.png" class="close"></a>
            <form class="form1" method="post" action="../../userCabinet.jsp" id="formRegistr">
                <ul class="dopMenu">
                    <li><a href="#" id="borderRegistr">Регистрация</a></li>
                    <li><a href="#popup1" onclick="clearP()">Войти</a></li>
                    <li><a href="#popup2" onclick="clearP()">Забыли пароль?</a></li>
                </ul>
                <input type="text" name="mail" placeholder="E-mail"  value="" id="eEmail"><img src="images/help.png" id="iconE">
                <div class="helpEmail">
                    Для проверки регистрации и в целях безопасности нам нужен адрес вашей электропочты.
                </div>
                <p id="erEmail"></p>
                <input type="text" name="login" placeholder="Логин" value="" id="eLogin"><img src="images/help.png" id="iconL">
                <div class="helpLogin">
                    Может состоять только из букв (A-Z a-z), цифр (0-9). Знак подчеркивания (_) лучше не использовать.
                    Длина логина не может быть меньше 3 и больше 30 символов.
                </div>
                <p id="erLogin"></p>
                <input type="password" name="password" placeholder="Пароль" value="" id="ePassword"><img src="images/help.png" id="iconP">
                <div class="helpPassword">
                    Должен содержать не менее 5 символов и не может совпадать с логином. Не используйте простые пароли, будьте разумны.
                </div>
                <p id="erPassword"></p>
                <input type="password" name="password2" placeholder="Повторите пароль" value="" id="eRepeatPass">
                <p id="erRepeatPass"></p>
                <input type="hidden" name="idU"  value="" id="idUserReg">
                <button type="button" class="buttEnter" onclick="redirectRegistr()">Зарегистрироваться</button>
            </form>
        </div>
    </div>
    <!--voiti popup-->
    <div id="popup1">
        <div class="window1">
            <a href="#"> <img src="images/CrossClose.png" class="close"></a>
            <form class="form1" method="post" action="../../userCabinet.jsp" id="formEnter">
                <ul class="dopMenu">
                    <li><a href="#popup" onclick="clearP()">Регистрация</a></li>
                    <li><a href="#" id="borderEnter">Войти</a></li>
                    <li><a href="#popup2" onclick="clearP()">Забыли пароль?</a></li>
                </ul>
                <input type="text" name="login" placeholder="Логин"  value="" id="loginE">
                <p id="erLoginE"></p>
                <input type="password" name="password" placeholder="Пароль" value="" id="passwordE">
                <p id="erPasswordE"></p>
                <input type="hidden" name="idU"  value="" id="idUserEnt">
                <button type="button" id="buttEnter" class="buttEnter" onclick="redirectEnter()">Войти</button>
            </form>
        </div>
    </div>

    <!--vspomnit parol popup-->
    <div id="popup2">
        <div class="window1">
            <a href="#"> <img src="images/CrossClose.png" class="close"></a>
            <form class="form1">
                <ul class="dopMenu">
                    <li><a href="#popup" onclick="clearP()">Регистрация</a></li>
                    <li><a href="#popup1" onclick="clearP()">Войти</a></li>
                    <li><a href="#" id="borderPassw">Забыли пароль?</a></li>
                </ul>
                <input type="text" name="mail" placeholder="Ваш E-mail" value="" id="eZabulPassw">
                <p id="erZabulPassw"></p>
                <input type="password" name="password" placeholder="Введите новый пароль" value="" id="newPassw">
                <p id="erZabulPassw1"></p>
                <button type="button" class="buttEnter" onclick="zabylPassword()">Получить ссылку на изменения пароля</button>
            </form>
        </div>
    </div>

                                                                <!-------------CONTENT------------->
    <div id="content">
        <h1>sokr.by</h1>
        <p id="text">
                Это сервис коротких ссылок. С помощью sokr.by вы можете из длинной ссылки получить ссылку,
            состоящую буквально из нескольких символов.
            Сервис особенно полезен в тех случаях, когда есть ограничения на количество вводимых символов и нет
            возможности указать в тексте длинную ссылку. Например, с помощью sokr.by можно укоротить любую ссылку и
            вставить короткую ссылку в Twitter или в SMS, где есть ограничение на количество вводимых символов.
            Предостовляется возможность использовать теги, для быстрого поиска необходимых ссылок. При сокращении ссылки
            автоматически генерируется QR-код. Для того чтобы сократить ссылку следует зарегистрироваться. Также
            сервис коротких ссылок идеален в случаях, когда вам необходимо скрыть ссылки спонсоров, замаскировать ссылку и т.д..
        </p>

        <!--Koll ssylok i perehodov animaciya-->
        <div class="kollRefClic">
            <ul class="text-animation">
                <jsp:useBean id="allRef" class="classes.References"/>
                <c:catch>
                <c:set var="allRefAndClick" value="${allRef.countRefAndClick()}"/>
                <c:forEach var="entry" items="${allRefAndClick}">
                <li>Колличество ссылок ${entry.key}</li>
                <li>Кликов ${entry.value}</li>
                </c:forEach>
                </c:catch>
            </ul>
        </div>

        <!--Vyvod vseh ssylok-->
            <div class="refAll">
                <ul>
                    <c:catch>
                    <c:set var="allRefere" value="${allRef.showAllRefer()}"/>
                    <c:forEach var="entry" items="${allRefere}">
                    <li><img src="images/cut.png" id="arDown"><p id="open">Нажать</p><a href="../../ReferRedirect?id=${entry.key}" target="_blank"  id="picture">${entry.value.getCut_ref()}</a>
                        <ul id="refAll1">
                            <li>
                                <table>
                                    <tr>
                                        <td class="yach1">Пользователь:</td>
                                        <td class="yach2">${entry.value.getLogin()}</td>
                                    </tr>
                                    <c:if test="${not empty entry.value.getDescription()}">
                                    <tr>
                                        <td class="yach1">Описание:</td>
                                        <td class="yach2"><div class="wrapDescr">${entry.value.getDescription()}</div></td>
                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td class="yach1">Оригинал:</td>
                                       <td class="yach2"> <div class="wrapDescr">${entry.value.getFull_ref()}</div></td>
                                    </tr>
                                    <c:if test="${not empty entry.value.getTag()}">
                                    <tr>
                                        <td class="yach1">Тег:</td>
                                        <td class="yach2">
                                            <form name="RedUserCab" method="post" action="../../UserTags" id="col">
                                                <input type="hidden" name="loginTags" value="${entry.value.getLogin()}" id="lT">
                                                <input type="hidden" name="login" value="${login}" id="l">
                                                <input type="hidden" name="tag" value="${entry.value.getTag()}" id="t">
                                                <input type="hidden" name="where" value="${where}" id="w">
                                                <input type="hidden" name="idU" value="${idU}" id="u">
                                                <button id="btag">#${entry.value.getTag()}</button>
                                           </form>
                                        </td>
                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td class="yach1">QR</td>
                                        <td class="yach2"><img alt="QR code" src="ReferQR?id=<c:out value="${entry.key}"/>"> </td>
                                    </tr>
                                </table>
                            </li>
                        </ul>
                    </li>
                    </c:forEach>
                    </c:catch>
                </ul>
            </div>

        <!--spisok tegov-->
        <c:if test="${not empty tagU}">
        <script> runIt() </script >
        <div id="tags">
            <div id="clo">
                <form name="clean" method="post" action="../../UserTags">
                    <input type="hidden" name="where" value="${where}">
                    <img src="images/standardbutton-close-32.png" id="clk">
                </form>
            </div>
            <table class="spisokTags">
                <c:forEach var="entry" items="${tagU}">
                    <c:choose>
                        <c:when test="${i%2!=0}">
                            <p style="display: none"> ${i=i+1}</p>
                            <tr class="spisokTagsColor1">
                                <td class="col1">Пользователь: </td>
                                <td class="colTag col2">${entry.value.getLogin()}</td>
                            </tr>
                            <tr class="spisokTagsColor1">
                                <td class="col1">Ссылка: </td>
                                <td class="col2"><a href="../../ReferRedirect?id=${entry.key.getId()}" target="_blank">${entry.value.getCut_ref()}</a></td>
                            </tr>
                            <tr class="spisokTagsColor1">
                                <td class="col1">Тег: </td>
                                <td class="colTag col2">#${tag}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <p style="display: none"> ${i=i+1}</p>
                            <tr class="spisokTagsColor2">
                                <td class="col1">Пользователь: </td>
                                <td class="colTag col2">${entry.value.getLogin()}</td>
                            </tr>
                            <tr class="spisokTagsColor2">
                                <td class="col1">Ссылка: </td>
                                <td class="col2"><a href="../../ReferRedirect?id=${entry.key.getId()}" target="_blank">${entry.value.getCut_ref()}</a></td>
                            </tr>
                            <tr class="spisokTagsColor2">
                                <td class="col1">Тег: </td>
                                <td class="colTag col2">#${tag}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
        </div>
        </c:if>
                                                <!--PODVAL-->
    </div>
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
