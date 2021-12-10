function filterBookings(action) {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = action;
    table = document.getElementById("bookings");
    tr = table.getElementsByTagName("tr");


    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else if (action == 'ALL'){
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}