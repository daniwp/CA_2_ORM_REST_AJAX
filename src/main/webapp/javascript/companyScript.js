$(document).ready(function () {

    getCompanies();

    function getCompanies() {

        $('#companybody').html("");

        $.ajax({
            url: 'http://localhost:8084/CA2REST/api/company/complete',
            type: "GET",
            dataType: "json",
            success: function (m) {
                m.forEach(function (company) {
                    var coutput = "";
                    coutput += typeof company.id !== "undefined" ? '<td>' + company.id + '</td>' : '<td>-</td>';
                    coutput += typeof company.name !== "undefined" ? '<td>' + company.name + '</td>' : '<td>-</td>';
                    coutput += typeof company.email !== "undefined" ? '<td>' + company.email + '</td>' : '<td>-</td>';
                    coutput += typeof company.description !== "undefined" ? '<td>' + company.description + '</td>' : '<td>-</td>';
                    coutput += typeof company.cvr !== "undefined" ? '<td>' + company.cvr + '</td>' : '<td>-</td>';
                    coutput += typeof company.numEmployees !== "undefined" ? '<td>' + formatNumber2(company.numEmployees) + '</td>' : '<td>-</td>';
                    coutput += typeof company.marketValue !== "undefined" ? '<td>' + formatNumber(company.marketValue) + '$' + '</td>' : '<td>-</td>';
                    $('#companybody').append('<tr>' + coutput + '</tr>');
                });
            }, error: function (res) {
                console.log(res);
            }
        });
    }

    $("#addCompany").click(function (data) {
        var name = $("#companyName").val();
        var description = $("#companyDescription").val();
        var email = $("#companyEmail").val();
        var cvr = $("#companyCVR").val();
        var employees = $("#companyEmployees").val();
        var marketValue = $("#companyMarketValue").val();
//        var street = $("#companyStreet").val();
//        var zipcode = $("#companyZipcode").val();
//        var city = $("#companyCity").val();

        $.ajax({
            url: "http://localhost:8084/CA2REST/api/company",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: name,
                description: description,
                email: email,
                cvr: cvr,
                numEmployees: employees,
                marketValue: marketValue,
//                street: street,
//                zipcode: zipcode,
//                city: city
            }),
            success: function (person) {
                getCompanies();
            }, error: function (res) {
                console.log(res);
            }
        });
    });

    $("#delete-company").click(function (data) {
        var ID = $("#company-id-delete").val();
        $.ajax({
            url: "http://localhost:8084/CA2REST/api/company/" + ID,
            type: "DELETE",
            success: function (data) {
                getCompanies();
            }, error: function (res) {
                console.log(res);
            }
        });
    });

    $('#hide-resuslts').on('click', function () {
        $('#search-table-company').css('display', 'none');
    });

    $('#searchCVR').on('click', function () {
        var cvr = $('#cvrsearch').val();
        getCompanyCVR(cvr);
    });

    $('#searchPhone').on('click', function () {
        var number = $('#phonesearch').val();
        getCompanyPhone(number);
    });

    $('#searchEmployee').on('click', function () {
        var count = $('#employeesearch').val();
        getCompaniesEmployee(count);
    });

    function getCompanyCVR(cvr) {
        $('#search-table-data-company').html("");

        $.ajax({
            url: 'http://localhost:8084/CA2REST/api/company/complete/cvr/' + cvr,
            type: "GET",
            dataType: "json",
            success: function (company) {
                console.log(company);
                var coutput = "";
                coutput += typeof company.id !== "undefined" ? '<td>' + company.id + '</td>' : '<td>-</td>';
                coutput += typeof company.name !== "undefined" ? '<td>' + company.name + '</td>' : '<td>-</td>';
                coutput += typeof company.email !== "undefined" ? '<td>' + company.email + '</td>' : '<td>-</td>';
                coutput += typeof company.description !== "undefined" ? '<td>' + company.description + '</td>' : '<td>-</td>';
                coutput += typeof company.cvr !== "undefined" ? '<td>' + company.cvr + '</td>' : '<td>-</td>';
                coutput += typeof company.numEmployees !== "undefined" ? '<td>' + formatNumber2(company.numEmployees) + '</td>' : '<td>-</td>';
                coutput += typeof company.marketValue !== "undefined" ? '<td>' + formatNumber(company.marketValue) + '$' + '</td>' : '<td>-</td>';
                $('#search-table-data-company').html('<tr>' + coutput + '</tr>');
                $('#search-table-company').css('display', 'block');
            }, error: function (res) {
                console.log(res);
            }
        });
    }

    function getCompanyPhone(phone_number) {
        $('#search-table-data-company').html("");

        $.ajax({
            url: 'http://localhost:8084/CA2REST/api/company/complete/phone/' + phone_number,
            type: "GET",
            dataType: "json",
            success: function (company) {
                console.log(company);
                var coutput = "";
                coutput += typeof company.id !== "undefined" ? '<td>' + company.id + '</td>' : '<td>-</td>';
                coutput += typeof company.name !== "undefined" ? '<td>' + company.name + '</td>' : '<td>-</td>';
                coutput += typeof company.email !== "undefined" ? '<td>' + company.email + '</td>' : '<td>-</td>';
                coutput += typeof company.description !== "undefined" ? '<td>' + company.description + '</td>' : '<td>-</td>';
                coutput += typeof company.cvr !== "undefined" ? '<td>' + company.cvr + '</td>' : '<td>-</td>';
                coutput += typeof company.numEmployees !== "undefined" ? '<td>' + formatNumber2(company.numEmployees) + '</td>' : '<td>-</td>';
                coutput += typeof company.marketValue !== "undefined" ? '<td>' + formatNumber(company.marketValue) + '$' + '</td>' : '<td>-</td>';
                $('#search-table-data-company').html('<tr>' + coutput + '</tr>');
                $('#search-table-company').css('display', 'block');
            }, error: function (res) {
                console.log(res);
            }
        });
    }

    function getCompaniesEmployee(employee_count) {
        $('#search-table-data-company').html("");

        $.ajax({
            url: 'http://localhost:8084/CA2REST/api/company/complete/employees/' + employee_count,
            type: "GET",
            dataType: "json",
            success: function (m) {
                m.forEach(function (company) {
                    var coutput = "";
                    coutput += typeof company.id !== "undefined" ? '<td>' + company.id + '</td>' : '<td>-</td>';
                    coutput += typeof company.name !== "undefined" ? '<td>' + company.name + '</td>' : '<td>-</td>';
                    coutput += typeof company.email !== "undefined" ? '<td>' + company.email + '</td>' : '<td>-</td>';
                    coutput += typeof company.description !== "undefined" ? '<td>' + company.description + '</td>' : '<td>-</td>';
                    coutput += typeof company.cvr !== "undefined" ? '<td>' + company.cvr + '</td>' : '<td>-</td>';
                    coutput += typeof company.numEmployees !== "undefined" ? '<td>' + formatNumber2(company.numEmployees) + '</td>' : '<td>-</td>';
                    coutput += typeof company.marketValue !== "undefined" ? '<td>' + formatNumber(company.marketValue) + '$' + '</td>' : '<td>-</td>';
                    $('#search-table-data-company').append('<tr>' + coutput + '</tr>');
                });
                $('#search-table-company').css('display', 'block');
            }, error: function (res) {
                console.log(res);
            }
        });
    }

    function formatNumber(number) {
        number = number.toFixed(2) + '';
        x = number.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
    }

    function formatNumber2(number) {
        number = number.toFixed(2) + '';
        x = number.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1;
    }

});


