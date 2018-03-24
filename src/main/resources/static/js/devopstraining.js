$( document).ready(main);

function main() {

    $('.btn-collapse').click(function (e) {
        e.preventDefault();
        var $this = $(this);
        var $collapse = $this.closest('.collapse-group').find('.collapse');
        $collapse.collapse('toggle');
        /* Change the button text on click */
        if ($.trim($(this).text()) === 'View details >>') {
            $(this).text('<< Hide details');
        } else {
            $(this).text('View details >>');
        }
    })
}

