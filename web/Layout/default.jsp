<%-- 
    Document   : default
    Created on : 07/06/2018, 17:11:20
    Author     : jefferson
--%>
<link rel="shortcut icon" type="image/png" href="/cantinho-do-pito/resources/img/logo.png"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="/cantinho-do-pito/resources/css/materialize.min.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="/cantinho-do-pito/resources/css/ricardo.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="/cantinho-do-pito/resources/css/jefferson.css"  media="screen,projection"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="/cantinho-do-pito/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/cantinho-do-pito/resources/js/materialize.min.js"></script>
<script type="text/javascript" src="/cantinho-do-pito/resources/js/jquery.mask.js"></script>

<script>
    $(document).ready(function(){
        $('.materialboxed').materialbox();
    });
  
    $(document).ready(function () {
        $('select').formSelect();
        $('#zipcode').mask('00000-000');
        $('#cellphone').mask('(00) 00000-0000');
        $('#cpf').mask('000.000.000-00');
    });

    $(document).ready(function () {
        var datepicker_pt_br = {
            today: 'Hoje',
            cancel: 'Cancelar',
            clear: 'Limpar',
            done: 'Ok',
            nextMonth: 'Próximo mês',
            previousMonth: 'Mês anterior',
            weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
            weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
            weekdays: [
                'Domingo',
                'Segunda-Feira',
                'Terça-Feira',
                'Quarta-Feira',
                'Quinta-Feira',
                'Sexta-Feira',
                'Sábado'
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
                'Março',
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
        
        var date = new Date(); 
        date.setDate(date.getDate());
        date.setMonth(date.getMonth());
        date.setFullYear(date.getFullYear() - 18);
        
        var options = {
            container: 'body',
            format: 'dd-mm-yyyy',
            yearRange: 100,
            maxDay: date.getDay(),
            maxMonth: date.getMonth(),
            maxYear: date.getFullYear(),
            showClearBtn: true,
            showDaysInNextAndPreviousMonths: true,
            i18n: datepicker_pt_br,
        }
        $(".datepicker").datepicker(options);
    });


    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('.sidenav');
        var instances = M.Sidenav.init(elems, options);
    });

    // Initialize collapsible (uncomment the lines below if you use the dropdown variation)
    // var collapsibleElem = document.querySelector('.collapsible');
    // var collapsibleInstance = M.Collapsible.init(collapsibleElem, options);

    // Or with jQuery

    $(document).ready(function () {
        $('.sidenav').sidenav();
    });
</script>