/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function crearGrafico() {
    var jsonParam = {'grafico_horas': true};
    $.ajax({
        async: true,
        type: 'POST',
        url: 'grafico',
        data: jsonParam,
        success: function () {
            
        },
        error: function (response) {
            responseError(response);
        }
    });



}
