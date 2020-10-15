/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    cambiarImagen();



});

function enviarCorreo(cliente,id_usuario,minutos) {
    var jsonParam = {'correo': true, 'cliente': cliente,'usuario':id_usuario,'minutos':minutos};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'correo',
        data: jsonParam,
        success: function (data) {
//            alert(JSON.stringify(data));
            console.log("exito");

        },
        error: function (response) {
            console.log("Error");
            console.log(response);
        }
    });



}
function notificaciones(nombre) {
    if ("Notification" in window) {
        Notification.requestPermission(function (result) {
            if (result === 'denied') {
                console.log('Denegado');
                Notification.requestPermission();
                return;
            } else if (result === 'default') {
                console.log('Aun no se establece');
                return;
            } else {
                var titulo = "Alerta! Alerta!";
                var cuerpo = {
                    icon: "img/logo.png",
                    body: "El cliente " + nombre + " no esta enviando capturas"
                };
                var alerta = new Notification(titulo, cuerpo);
                setTimeout(function () {
                    alerta.close()
                }, 5000);
                console.log("permiso concedido");

            }
        });

    } else {
        console.log("No soporta los permisos");

    }
//  

}
function popupImagen(event, nombre, apellido, id_img, fecha) {
    var srcimg = $(event).attr("src");
    var myModal = $('#myModal');
    var imgModal = $('#img01');
    myModal.css("display", "block");
    var a;
    var jsonParam = {'unaImagen': true, 'id_img': id_img};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'imagen',
        data: jsonParam,
        success: function (data) {
//             alert(JSON.stringify(data.imagen[0].img));
            a = data.imagen[0].img;
            imgModal.attr("src", ("data:image/jpeg;base64," + a));
        },
        error: function (response) {
            responseError(response);
        }
    });

}

function cerrarsesion() {
    var jsonParam = {'cerrar': true};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'imagen',
        data: jsonParam,
        success: function () {
            location.reload();
        },
        error: function (response) {
            responseError(response);
        }
    });

}
function datosGrafico() {
    var jsonParam = {'grafico_horas': true};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'grafico',
        data: jsonParam,
        success: function (data) {
//            alert(JSON.stringify(data));
            for (var i = 0; i < data.actividad.length; i++) {
                var id_usuario = data.actividad[i].id_usuario;
                var datoos = [data.actividad[i].actividad, data.actividad[i].inactividad];
                grafico(datoos, id_usuario);
            }


        },
        error: function (response) {
            responseError(response);
        }
    });



}
function grafico(datoos, id_usuario) {
    new Chart(document.getElementById("grafico" + id_usuario), {
        type: 'pie',
        data: {
            labels: {
                render: 'percentage',
                fontColor: ['green', 'white'],
                precision: 2
            },
            datasets: [{
                    label: "Population (millions)",
                    backgroundColor: ["#40ff00", "#ff0004"],
                    data: datoos
                }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            title: {
                display: true,
//                text: 'Hora de actividad'
            },
            tooltips: {
                enabled: false
            }
        }
    });
}
function cambiarImagen() {
    var jsonParam = {'imagen': true};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'imagen',
        data: jsonParam,
        success: function (data) {
//            alert(JSON.stringify(data.imagen));
                for (var i = 0; i < data.imagen.length; i++) {
                    var imagen = data.imagen[i].img;
                var fecha = data.imagen[i].fecha;
                var nombre = data.imagen[i].nombre_usuario;
                var apellido = data.imagen[i].apellido;
                var id_usuario = data.imagen[i].id_usuario;
                var id_img = data.imagen[i].id_imagen;
                var nombre_imagen = data.imagen[i].nombre_imagen;
                var diferencia_minutos = data.imagen[i].diferencia_minutos;
                var body = $('body');
                var div = $('<div id="contenedor_imagen' + id_usuario + '" class="contenedores" > </div>');
//                var contenedorImagen = '<img id="img-01" style="width:100%;cursor:pointer;" src="data:image/jpeg;base64,' + imagen + '" onclick="popupImagen(this,\'' + nombre + '\',\'' + apellido + '\',\'' + fecha + '\')">\n\
                var contenedorImagen = '<img id="img-01" style="width:100%;cursor:pointer;" src="http://200.75.13.14:8082/Capturas/Imagenes/' + nombre_imagen + '" onclick="popupImagen(this,\'' + nombre + '\',\'' + apellido + '\',\'' + id_img + '\',\'' + fecha + '\')">\n\
                                              <div><span class="datos">' + fecha + ' - ' + nombre + ' ' + apellido + '</span>\n\
                                        <div class="chart-container"><canvas id="grafico' + id_usuario + '"  width="150" height="80"></canvas></div></div>';
                div.append(contenedorImagen)
                body.append(div);
                datosGrafico();
                console.log("Usuario: " + nombre + " Fecha: " + fecha);
                console.log(fecha.split('-'));
                console.log("diferencia: " + diferencia_minutos);
                var nombrecompleto = nombre + " " + apellido;
                if (diferencia_minutos > 3 & diferencia_minutos <= 10) {

//                    notificaciones(nombrecompleto);
                    enviarCorreo(nombrecompleto,id_usuario,1)

                } else if (diferencia_minutos == 12) {
                    console.log("Mandar el correo por " + nombrecompleto+ " id_usuario:"+id_usuario);
                    enviarCorreo(nombrecompleto,id_usuario,2)
                }
            }
//            $('body').append('<div class="contenedores"><canvas id="grafico"></canvas></div>');

        },
        error: function (response) {
            responseError(response);
        }
    });
}
function cerrar(event) {
    $('#myModal').css("display", "none");


}

