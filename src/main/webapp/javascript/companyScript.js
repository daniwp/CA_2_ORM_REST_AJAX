 $(document).ready(function(){
     
 $.ajax({
        url: 'http://localhost:8084/CA2REST/api/company/complete',
        type: "GET",
        dataType: "json",
        success: function (m) {
            m.forEach(function (company) {
                var coutput = "";
                coutput += typeof company.name !== "undefined" ? '<td>' + company.name + '</td>' : '<td>-</td>';
                coutput += typeof company.email !== "undefined" ? '<td>' + company.email + '</td>' : '<td>-</td>';
                coutput += typeof company.description !== "undefined" ? '<td>' + company.description + '</td>' : '<td>-</td>';
                coutput += typeof company.cvr !== "undefined" ? '<td>' + company.cvr + '</td>' : '<td>-</td>';
                coutput += typeof company.numEmployees !== "undefined" ? '<td>' + company.numEmployees + '</td>' : '<td>-</td>';
                coutput += typeof company.marketValue !== "undefined" ? '<td>' + company.marketValue + '</td>' : '<td>-</td>';
                $('#companybody').append('<tr>' + coutput + '</tr>');
                //console.log(coutput);
            });
        }, error: function (res) {
            console.log(res);
        }
    });
 });