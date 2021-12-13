function search(searchField, searchIn){
    let input, filter, table, tr, td, txtValue;
    input = document.getElementById(searchField);
    filter = input.value.toUpperCase();
    table = document.getElementById(searchIn);
    tr = table.getElementsByTagName("tr");

    for (let i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

