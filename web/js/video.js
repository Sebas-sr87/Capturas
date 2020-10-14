/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function crearVideo(cliente) {
    var jsonParam = {'video': true, 'cliente': cliente};
    $.ajax({
        async: false,
        type: 'POST',
        url: 'Logindos',
        data: jsonParam,
        success: function (data) {
            console.log("termino")
//             alert(JSON.stringify(data));
                 var nombre=data.imagen[0].nombre;
                 console.log(nombre);
             
            var div=$('#contenedor_video');
            $('.video_contenedor').remove();
            div.append('<video class="video_contenedor" src="http://200.75.13.14:8082/Capturas/Videos/'+nombre+'" controls></video>');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error");
            console.log(jqXHR.toString());
            console.log(textStatus.toString());
            console.log(errorThrown.toString());
        }
    });



}