<%@ include file="header.jsp" %>

<div class="section section-persons">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="page-header">
                    <h2>Personregister</h2>
                </div>
                <p style="color: black; font-family: times new roman; font-size: 18px;">Infomation om personer i PET's kriminalregister</p>

                <table id="data" class="table table-hover">
                    <thead>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Email</th>
                            <th>Zipcode</th>
                            <th>Street</th>
                            <th>City</th>
                        </tr>
                    </thead>
                    <tbody id="person"></tbody>
                </table>
                <div class="row">
                    <div class="col-md-2">
                        <a href="http://localhost:8084/CA2REST/people.jsp" class="btn btn-primary btn-block horizontal-align">
                            <i class="fa fa-refresh" aria-hidden="true"></i> Update</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="page-header">
                    <h2>Kontakt informationer</h2>
                </div>
                <p style="color: black; font-family: times new roman; font-size: 18px;">Kantakt info</p>

                <table id="contact" class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody id="contact"></tbody>
                </table>
                <button type="button" class="btn btn-primary btn-block">
                    <span id="phonelist" class="glyphicon glyphicon-list"> Phonelist</span>
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>