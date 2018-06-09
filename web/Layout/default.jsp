<%-- 
    Document   : default
    Created on : 07/06/2018, 17:11:20
    Author     : jefferson
--%>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="/cantinho-do-pito/resources/css/materialize.min.css"  media="screen,projection"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="/cantinho-do-pito/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/cantinho-do-pito/resources/js/materialize.min.js"></script>
<script type="text/javascript" src="/cantinho-do-pito/resources/js/jquery.mask.js"></script>

<script>
    $(document).ready(function(){
        $('select').formSelect();
         $('#zipcode').mask('00000-000');
         $('#cellphone').mask('(00) 00000-0000');
         $('#cpf').mask('000.000.000-00');
    });
    
    $(document).ready(function(){
        var datepicker_pt_br = {
            today: 'Hoje',
            cancel: 'Cancelar',
            clear: 'Limpar',
            done: 'Ok',
            nextMonth: 'Pr�ximo m�s',
            previousMonth: 'M�s anterior',
            weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'], 
            weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S�b'], 
            weekdays: [
                'Domingo', 
                'Segunda-Feira', 
                'Ter�a-Feira', 
                'Quarta-Feira', 
                'Quinta-Feira', 
                'Sexta-Feira', 
                'S�bado'
            ], 
            monthsShort: [
                'Jan', 
                'Fev', 
                'Mar', 
                'Abr', 
                'Mai', 
                'Jun', 
                'Jul', 
                'Ago', 
                'Set', 
                'Out', 
                'Nov', 
                'Dez'
            ], 
            months: [
                'Janeiro', 
                'Fevereiro', 
                'Mar�o', 
                'Abril', 
                'Maio', 
                'Junho', 
                'Julho', 
                'Agosto', 
                'Setembro', 
                'Outubro', 
                'Novembro', 
                'Dezembro'
            ]
        }
        var options = {
            container: 'body',
            format: 'dd/mm/yyyy',
            showDaysInNextAndPreviousMonths: true,
            i18n: datepicker_pt_br,
        }
        $(".datepicker").datepicker(options);
    });
    
</script>