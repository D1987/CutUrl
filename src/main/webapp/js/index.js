// MAIN PAGE

/*ochistka polei*/
function clearP(){
    //dlya registracii
    document.getElementById('eEmail').value = "";
    document.getElementById('eLogin').value = "";
    document.getElementById('ePassword').value = "";
    document.getElementById('eRepeatPass').value = "";

    document.getElementById('erEmail').style.display = 'none';
    document.getElementById('erLogin').style.display = 'none';
    document.getElementById('erPassword').style.display = 'none';
    document.getElementById('erRepeatPass').style.display = 'none';
    //dlya vhoda
    document.getElementById('loginE').value = "";
    document.getElementById('passwordE').value = "";

    document.getElementById('erLoginE').style.display = 'none';
    document.getElementById('erPasswordE').style.display = 'none';
    //dlya polucheniya password
    document.getElementById('eZabulPassw').value = "";
    document.getElementById('newPassw').value = "";

    document.getElementById('erZabulPassw').style.display = 'none';
    document.getElementById('erZabulPassw1').style.display = 'none';
}
//ajax proverka polei registracii
function redirectRegistr(){
    $.ajax({
        url:"../../UserRegistrac",
        type: "post",
        data:({login: $('#eLogin').val(), password:$('#ePassword').val(), mail:$('#eEmail').val(), password2:$('#eRepeatPass').val()}),
        dataType:"html",
        success: function (errorEnter){
            if(errorEnter === "mailIs")
            {
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erEmail').style.display = 'block';
                document.getElementById('erEmail').innerHTML = 'Такой email уже занят!';
            }
            else if (errorEnter === "notMail"){
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erEmail').style.display = 'block';
                document.getElementById('erEmail').innerHTML = 'Такой e-mail не существует!';
            }
            else if (errorEnter === "mail"){
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erEmail').style.display = 'block';
                document.getElementById('erEmail').innerHTML = 'Введите e-mail!';
            }
            else if (errorEnter === "netTakoyiPochty"){
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erEmail').style.display = 'block';
                document.getElementById('erEmail').innerHTML = 'E-mail не корректен!';
            }
            else if (errorEnter === "loginIs"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erLogin').style.display = 'block';
                document.getElementById('erLogin').innerHTML = 'Логин уже занят!';
            }
            else if (errorEnter === "login"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erLogin').style.display = 'block';
                document.getElementById('erLogin').innerHTML = 'Введите логин';
            }
            else if (errorEnter === "anotherSymDlinna"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erLogin').style.display = 'block';
                document.getElementById('erLogin').innerHTML = 'Недопустимые символы или неверная длинна!';
            }
            else if (errorEnter === "password"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erPassword').style.display = 'block';
                document.getElementById('erPassword').innerHTML = 'Введите пароль!';
            }
            else if (errorEnter === "passwordDlinna"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erPassword').style.display = 'block';
                document.getElementById('erPassword').innerHTML = 'Длинна пароля должна быть более 5 символов!';
            }
            else if (errorEnter === "passwordLogin"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'none';
                document.getElementById('erPassword').style.display = 'block';
                document.getElementById('erPassword').innerHTML = 'Пароль не должен совпадать с логином!';
            }
            else if (errorEnter === "password2"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'block';
                document.getElementById('erRepeatPass').innerHTML = 'Повторите пароль';
            }
            else if (errorEnter === "passwordPass2"){
                document.getElementById('erEmail').style.display = 'none';
                document.getElementById('erLogin').style.display = 'none';
                document.getElementById('erPassword').style.display = 'none';
                document.getElementById('erRepeatPass').style.display = 'block';
                document.getElementById('erRepeatPass').innerHTML = 'Пароли не совпадают!';
            }
            else
            {
                document.getElementById('erLoginE').style.display = 'none';
                document.getElementById('erPasswordE').style.display = 'none';
                window.location.href="registr.jsp?mail=" + $('#eEmail').val();
            }
        }
    });
}
// ajax first vhod v person cabinet
function pervuyVhod(){
    $.ajax({
        url:"../../UserFirstEnter",
        type: "post",
        data:({login: $('#eLogin').val(), password:$('#ePassword').val(), mail:$('#eEmail').val()}),
        dataType:"html",
        success: function (errorEnter){
            document.getElementById('idUserReg').value = errorEnter;
            document.forms['formRegistr'].submit();
        }
    });
}
// ajax otpravka dannyh na vhod
function redirectEnter(){
    $.ajax({
        url:"../../UserAvtoriz",
        type: "post",
        data:({login: $('#loginE').val(), password:$('#passwordE').val()}),
        dataType:"html",
        success: function (errorEnter){
            if(errorEnter === "true")
            {
                document.getElementById('erLoginE').style.display = 'none';
                document.getElementById('erPasswordE').style.display = 'block';
                document.getElementById('erPasswordE').innerHTML = 'Неверный логин или пароль!';
            }else if (errorEnter === "pustoiLogin"){
                document.getElementById('erPasswordE').style.display = 'none';
                document.getElementById('erLoginE').style.display = 'block';
                document.getElementById('erLoginE').innerHTML = 'Введите логин!';
            }
            else if (errorEnter === "pustoiPassword"){
                document.getElementById('erLoginE').style.display = 'none';
                document.getElementById('erPasswordE').style.display = 'block';
                document.getElementById('erPasswordE').innerHTML = 'Введите пароль!';
            }
            else
            {
                var mas = errorEnter.split('/');
                document.getElementById('erLoginE').style.display = 'none';
                document.getElementById('erPasswordE').style.display = 'none';
                document.getElementById('idUserEnt').value = mas[0];
                document.getElementById('loginE').value = mas[1];
                document.forms["formEnter"].submit();
            }
        }
    });
}
/*ajax zabyl parol*/
function zabylPassword(){
    $.ajax({
        url:"../../UserSekret",
        type: "post",
        data:({mail: $('#eZabulPassw').val(),password: $('#newPassw').val()}),
        dataType:"html",
        success: function (errorEnter){
            if(errorEnter === "true")
            {
                document.getElementById('erZabulPassw1').style.display = 'none';
                document.getElementById('erZabulPassw').style.display = 'block';
                document.getElementById('erZabulPassw').innerHTML = 'Поле не должно быть пустым!';
            }
            else if (errorEnter === "neKorrektno")
            {
                document.getElementById('erZabulPassw1').style.display = 'none';
                document.getElementById('erZabulPassw').style.display = 'block';
                document.getElementById('erZabulPassw').innerHTML = 'Проверьте корректность email!';
            }
            else if (errorEnter === "mailNo")
            {
                document.getElementById('erZabulPassw1').style.display = 'none';
                document.getElementById('erZabulPassw').style.display = 'block';
                document.getElementById('erZabulPassw').innerHTML = 'Такой e-mail не существует!';
            }
            else if (errorEnter === "password"){
                document.getElementById('erZabulPassw').style.display = 'none';
                document.getElementById('erZabulPassw1').style.display = 'block';
                document.getElementById('erZabulPassw1').innerHTML = 'Введите пароль!';
            }
            else if (errorEnter === "passwordIs"){
                document.getElementById('erZabulPassw').style.display = 'none';
                document.getElementById('erZabulPassw1').style.display = 'block';
                document.getElementById('erZabulPassw1').innerHTML = 'Такой пароль уже занят!';
            }
            else if (errorEnter === "loginEqPassw"){
                document.getElementById('erZabulPassw').style.display = 'none';
                document.getElementById('erZabulPassw1').style.display = 'block';
                document.getElementById('erZabulPassw1').innerHTML = 'Пароль не должен совпадать с логином!';
            }
            else if (errorEnter === "passwordDlinna"){
                document.getElementById('erZabulPassw').style.display = 'none';
                document.getElementById('erZabulPassw1').style.display = 'block';
                document.getElementById('erZabulPassw1').innerHTML = 'Длинна пароля должна быть более 5 символов!';
            }
            else
            {
                document.getElementById('erZabulPassw').style.display = 'none';
                document.getElementById('erZabulPassw1').style.display = 'block';
                document.getElementById('erZabulPassw1').innerHTML = 'Проверьте свою почту!';
            }
        }
    });
}
// show description
$(document).ready(function () {
    $(".refAll #arDown").click(
        function () {
            $(this).nextAll('ul').slideToggle('slow');
        });
});
//poyavlenie spiska tegov
function runIt() {
    $(".refAll").animate({marginLeft:'-=230',width: "350px"},1000);
    $(".refAll").find('a').animate({'padding-left' : "10px"},1000);
    setTimeout("$('#tags').slideDown('slow')", 850);
}
//close spiska tegov
$(document).on('click','#clk', function(){
    $("#tags").slideUp("fast");
    $('.refAll').animate({marginLeft:'+=230',width: '500px'},1800);
    $(".refAll").find('a').animate({'padding-left' : "80px"},1200);
    setTimeout(" document.forms['clean'].submit()", 2000);
});

/*PERSON CABINET*/

//show block update person data
function showConUpdate(element_id,element_id2) {
    document.getElementById(element_id).style.left = 0;
    document.getElementById(element_id2).style.left = 315;
}
//close block update person data
function closeButtonU(element_id,element_id2) {
    document.getElementById(element_id).style.left = -315;
    document.getElementById(element_id2).style.left = 0;

    document.getElementById('erNewLog').va = 'none';
    document.getElementById('erNewPass').style.display = 'none';
    document.getElementById('erNewPassRep').style.display = 'none';
    document.getElementById('textDelAkk').style.display = 'none';
    document.getElementById('bYDeleteU').style.display = 'none';
    document.getElementById('bNDeleteU').style.display = 'none';

    document.getElementById('loginNew').value = '';
    document.getElementById('passwordNew').value = '';
    document.getElementById('passwordNewRepeate').value = '';
}
// razloginitsya show
$(document).ready(function () {
    $("#blodOut").click(function () {
        $("#logOut").slideDown("slow");
    });
});
// razloginitsya hide
$(document).ready(function () {
    $("#bNo").click(function () {
        $("#logOut").slideUp("fast");
    });
});
//update ref
$(document).ready(function () {
    $(".tabl #upRef").click(function () {
        $("#updateRef").fadeToggle("fast");
    });
});
//close update ref
$(document).ready(function () {
    $('#bCloseR').click(function () {
        $("#updateRef").fadeToggle('slow');
        $("#erTagD").css('display','none');
    });
});
//show tags
function runIt1() {
    $("#tags").fadeToggle("fast");
}
//close spiska tegov
$(document).on('click','#bCTags', function(){
    $("#tags").fadeToggle("slow");
    setTimeout(" document.forms['clean'].submit()", 1000);
});
//show block obnovleniya ssylki
function showConUpdateRef(element_id2,num) {
    document.getElementById(element_id2).value = num;
}
// delete akkaunt
function deleteAkkaunt(element_id){
    document.getElementById(element_id).style.opacity = 1;
}
//button delete akkaunt
function bDeleteAkkauntNo(element_id){
    document.getElementById(element_id).style.opacity = 0;
}
//show block udalenie ref
function showDelRef(element_id,element_id2,num) {
    document.getElementById(element_id).style.display = 'block';
    document.getElementById(element_id2).value = num;
}
//ajax udalenie ref
function deleteRef(){
    $.ajax({
        url:"../../ReferDelete",
        type: "post",
        data:({id: $('#del').val(),idUser: $('#idUs').val()}),
        dataType:"html",
        success: (document.getElementById('winDel').style.display = 'none',
        setTimeout("location.reload()", 100))
    });
}
//hide block udalenie ref
function hideDelRef(element_id) {
    document.getElementById(element_id).style.display = 'none';
}
//ajax cut refrences
function cutssulk(){
    $.ajax({
        url:"../../ReferCut",
        type: "post",
        data:({login: $('#login').val(),ssylka:$('#ref').val(),description: $('#desc').val(),tag:$('#tag').val()}),
        dataType:"html",
        success: function (errorEnter){
            if(errorEnter === "pustayaSsylka")
            {
                document.getElementById('erSylka').innerHTML = 'Введите ссылку!';
            }
            else if(errorEnter === "neCorr"){
                document.getElementById('erSylka').innerHTML = 'Проверьте корректность ссылки!';
            }
            else {
                location.reload();
            }
        }
    });
}
//ajax update person data
function updatePersonData(){
    $.ajax({
        url:"../../UserUpdate",
        type: "post",
        data:({login: $('#loginNew').val(),password:$('#passwordNew').val(),password1:$('#passwordNewRepeate').val(),loginOld:$('#loginOld').val(),idU:$('#idU').val()}),
        dataType:"html",
        success: function (errorEnter){
            if (errorEnter === "loginIs"){
                document.getElementById('erNewPass').style.display = 'none';
                document.getElementById('erNewLog').style.display = 'block';
                document.getElementById('erNewLog').innerHTML = 'Логин уже занят!';
            }
            else if (errorEnter === "anotherSymDlinna"){
                document.getElementById('erNewPass').style.display = 'none';
                document.getElementById('erNewLog').style.display = 'block';
                document.getElementById('erNewLog').innerHTML = 'Недопустимые символы или неверная длинна!';
            }
            else if (errorEnter === "passwordDlinna"){
                document.getElementById('erNewLog').style.display = 'none';
                document.getElementById('erNewPass').style.display = 'block';
                document.getElementById('erNewPass').innerHTML = 'Длинна пароля должна быть более 5 символов!';
            }
            else if (errorEnter === "passwordLogin"){
                document.getElementById('erNewLog').style.display = 'none';
                document.getElementById('erNewPass').style.display = 'block';
                document.getElementById('erNewPass').innerHTML = 'Пароль не должен совпадать с логином!';
            }
            else if (errorEnter === "neEq"){
                document.getElementById('erNewLog').style.display = 'none';
                document.getElementById('erNewPass').style.display = 'block';
                document.getElementById('erNewPassRep').innerHTML = 'Пароли не совпадают!';
            }
            else if (errorEnter === "pusto"){
                document.getElementById('erNewLog').style.display = 'none';
                document.getElementById('erNewPass').style.display = 'block';
                document.getElementById('erNewPassRep').innerHTML = 'Хотя бы одно поле должно быть заполнено!';
            }
            else
            {
                if($('#loginNew').val() === ""){
                    window.location.href="userCabinet.jsp?login="+$('#loginOld').val() + "&idU=" + $('#idU').val();
                }
                else {
                    window.location.href = "userCabinet.jsp?login=" + $('#loginNew').val() + "&idU=" + $('#idU').val();
                }
            }
        }
    });
}
//ajax update description ssylki
function updateDescAndTags(){
    $.ajax({
        url:"../../ReferUpdate",
        type: "post",
        data:({login: $('#loginUpdRef').val(),description:$('#description').val(),tag:$('#tagU').val(),idR:$('#val').val()}),
        dataType:"html",
        success: function (errorEnter){
            if (errorEnter === "pustyePolya"){
                document.getElementById('erTagD').style.display = 'block';
                document.getElementById('erTagD').innerHTML = 'Пустые поля!';
            }
            else
            {
                location.reload();
            }
        }
    });
}
//skryt spisok tegov/ pokazat description/  uvelichit content
$(document).ready(function () {
    var hContent = $('.content').height();
    $(".menu_list").mouseenter(function(){
        $("#tags").hide('fast');
    });
});



