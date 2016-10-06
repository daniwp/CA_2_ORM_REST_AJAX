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
                        <a href="http://localhost:8084/CA2REST/" class="btn btn-primary btn-block horizontal-align">
                            <i class="fa fa-refresh" aria-hidden="true"></i> Update</span>
                        </a>
                    </div>
                </div>
            </div>
            <ul id="list" class="list-group"></ul>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>