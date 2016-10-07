<%@ include file="header.jsp" %>

<div class="section section-companies">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="page-header">
                    <h2>Company informationer</h2>
                </div>
                <table id="company" class="table table-hover">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Description</th>
                            <th>CVR</th>
                            <th>Employees</th>
                            <th>Market Value</th>
                        </tr>
                    </thead>
                    <tbody id="companybody"></tbody>
                </table>
                <div class="row">
                    <div class="col-md-2">
                        <a href="http://localhost:8084/CA2REST/companies.jsp" class="btn btn-primary btn-block horizontal-align">
                            <i class="fa fa-refresh" aria-hidden="true"></i> Update</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="page-header">
                    <h2>Add a Company</h2>
                </div>
                <div class="form-group">
                    <p>Name:</p>
                    <input id="companyName" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <p>Description:</p>
                    <input id="companyDescription" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <p>CVR:</p>
                    <input id="companyCVR" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <p>Email:</p>
                    <input id="companyEmail" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <p>Employees:</p>
                    <input id="companyEmployees" class="form-control" type="number"/>
                </div>
                <div class="form-group">
                    <p>Market Value:</p>
                    <input id="companyMarketValue" class="form-control" type="number"/>
                </div>
                <div class="form-group">
                    <p>Street:</p>
                    <input id="companyStreet" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <p>Zipcode:</p>
                    <input id="companyZipcode" class="form-control" type="number"/>
                </div>
                <div class="form-group">
                    <p>City:</p>
                    <input id="companyCity" class="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <button id="addCompany" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>