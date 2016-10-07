$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8084/CA2REST/api/person/complete',
        type: 'GET',
        dataType: "json",
        success: function (data) {
            //console.log(data);
            data.forEach(function (person) {

                var output = "";
                output += typeof person.firstname !== "undefined" ? '<td>' + person.firstname + '</td>' : '<td>-</td>';
                output += typeof person.lastname !== "undefined" ? '<td>' + person.lastname + '</td>' : '<td>-</td>';
                output += typeof person.email !== "undefined" ? '<td>' + person.email + '</td>' : '<td>-</td>';
                output += typeof person.zipcode !== "undefined" ? '<td>' + person.zipcode + '</td>' : '<td>-</td>';
                output += typeof person.street !== "undefined" ? '<td>' + person.street + '</td>' : '<td>-</td>';
                output += typeof person.city !== "undefined" ? '<td>' + person.city + '</td>' : '<td>-</td>';

                $('#person').append('<tr>' + output + '</tr>');
            });
        }, error: function (res) {
            alert("failure");
        }
    });

    $.ajax({
        url: 'http://localhost:8084/CA2REST/api/person/contactinfo',
        type: 'GET',
        dataType: "json",
        success: function (data) {
            //console.log(data);

            data.forEach(function (person) {
                var output = "";
                output += typeof person.id !== "undefined" ? '<td>' + person.id + '</td>' : '<td>-</td>';
                output += typeof person.name !== "undefined" ? '<td>' + person.name + '</td>' : '<td>-</td>';
                output += typeof person.email !== "undefined" ? '<td>' + person.email + '</td>' : '<td>-</td>';
                $('#contact').append('<tr>' + output + '</tr>');
            });
        }, error: function (res) {
            console.log("error in person table" + res);
        }

    });
    $.ajax({
        url: 'http://localhost:8084/CA2REST/api/person/contactinfo',
        type: 'GET',
        dataType: "json",
        success: function (data) {
            //console.log(data);
            $("#pbtn").click(function () {

                data.forEach(function (person) {
                    var output = "";
                    output += typeof person.id !== "undefined" ? '<ul>' + person.id + '</ul>' : '<ul>-</ul>';
                    output += typeof person.name !== "undefined" ? '<ul>' + person.name + '</ul>' : '<ul>-</ul>';
                    var phone = [];
                    phone = person.phones;
                    $.each(phone, function (p) {
                        console.log(phone);
                        output += typeof phone[p].description !== "undefined" ? '<ul>' + phone[p].description + '</ul>' : '<ul>-</ul>';
                        output += typeof phone[p].number !== "undefined" ? '<ul>' + phone[p].number + '</ul>' : '<ul>-</ul>';
                    });
                    $('#plist').append('<ul>' + output + '</ul>');
                });
            });
        }, error: function (res) {
            console.log("error in person table" + res);
        }

    });
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
    $("#add").click(function(data) {
        var fname = $("#firstname").val();
        var lname = $("#lastname").val();
        var email = $("#email").val();
        $.ajax({
            url: "http://localhost:8084/CA2REST/api/person",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                firstname: fname,
                lastname: lname,
                email: email
            }),
            success: function (person) {
                $("#contact").append(
                        '<td>' + person.firstname + '</td>'
                        + '<td>' + person.lastname + '</td>'
                        + '<td>' + person.email + '</td>'
                        );
                console.log(("Added to the db"));
                alert(person);
            }, error: function (res) {
                console.log(res);
                alert(res);    
            }
        });
        

    });
            $("#delete").click(function(data) {
        var ID = $("#ID").val();
        $.ajax({
            url: "http://localhost:8084/CA2REST/api/person/{id}",
            type: "DELETE",
            success: function(data) {
                    alert("You have now deleted a person");
            }, error: function(res) {
                console.log(res);
            }
            });
});
});

