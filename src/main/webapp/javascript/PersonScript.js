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
            console.log(res);
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
                var phone = [];
                phone = person.phones;
                $.each(phone, function(p) {
                    console.log(phone);
                    output += typeof phone[p].description !== "undefined" ? '<td>' + phone[p].description + '</td>' : '<td>-</td>';
                    output += typeof phone[p].number !== "undefined" ? '<td>' + phone[p].number + '</td>' : '<td>-</td>';
                });
                $('#contact').append('<tr>' + output + '</tr>');
            });
        }, error: function (res) {
            console.log(res);
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
                output += typeof person.id !== "undefined" ? '<ul>' + person.id + '</ul>' : '<ul>-</ul>';
                output += typeof person.name !== "undefined" ? '<ul>' + person.name + '</ul>' : '<ul>-</ul>';
                var phone = [];
                phone = person.phones;
                $.each(phone, function(p) {
                    console.log(phone);
                    output += typeof phone[p].description !== "undefined" ? '<ul>' + phone[p].description + '</ul>' : '<ul>-</ul>';
                    output += typeof phone[p].number !== "undefined" ? '<ul>' + phone[p].number + '</ul>' : '<ul>-</ul>';
                });
                $('#list').append('<ul>' + output + '</ul>');
            });
        }, error: function (res) {
            console.log(res);
        }

    });
    $("#list").css("display","none");
    $("#phonelist").click(function(){
        $("#list").css("display","block");
    });
});

