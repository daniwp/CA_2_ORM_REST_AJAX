<%@ include file="header.jsp" %>

<div class="section section-companies">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="row">
                    <div class="page-header">
                        <h2>Company informationer</h2>
                    </div>
                    <table id="company" class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
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
                        <div class="col-md-4">
                            <a href="http://localhost:8084/CA2REST/companies.jsp"
                               class="btn btn-primary btn-block">
                                <i class="fa fa-refresh" aria-hidden="true"></i> Update</span>
                            </a>
                        </div>
                        <div class="col-md-8">
                            <div class="col-md-8">
                                <div class="col-md-3">
                                    <p>Delete by ID:</p>
                                </div>
                                <div class="col-md-8">
                                    <input id="company-id-delete" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <button id="delete-company" class="btn btn-danger">X Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="page-header">
                        <h2>Search for specific Companies</h2>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <p>CVR:</p>
                                <input id="cvrsearch" type="number" class="form-control">
                            </div>
                            <button id="searchCVR" class="btn btn-primary btn-block"><i class="fa fa-search"
                                                                                        aria-hidden="true"></i>&nbsp;Search
                            </button>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <p>Phone number:</p>
                                <input id="phonesearch" type="number" class="form-control">
                            </div>
                            <button id="searchPhone" class="btn btn-primary btn-block"><i class="fa fa-search"
                                                                                          aria-hidden="true"></i>&nbsp;Search
                            </button>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <p>Employee count over:</p>
                                <input id="employeesearch" type="number" class="form-control">
                            </div>
                            <button id="searchEmployee" class="btn btn-primary btn-block"><i class="fa fa-search"
                                                                                             aria-hidden="true"></i>&nbsp;Search
                            </button>
                        </div>
                    </div>
                </div>
                <div id="search-table-company">
                    <div class="row">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Description</th>
                                <th>CVR</th>
                                <th>Employees</th>
                                <th>Market Value</th>
                            </tr>
                            </thead>
                            <tbody id="search-table-data-company">
                            </tbody>
                        </table>
                        <button id="hide-resuslts" class="btn btn-primary">Hide results</button>
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