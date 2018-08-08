<%@include file="head.jsp" %>

<body>
    <div class="container-fluid">



        <div class="row">
            <%@include file="mainmenu.jsp" %>

        </div>

        <script>



            $(document).ready(function () {
                $("#submitFormBtn").click(function () {
                    uploadFile();
                    return false;
                    

                });
                function _(el) {
                    return document.getElementById(el);
                }

                function uploadFile() {
                    var file = _("file1").files[0];
// alert(file.name+" | "+file.size+" | "+file.type);
                    var fileName = $("#fileName").val();
                    var caseId = $("#caseId").val();
                    var fileType = $("#fileType").val();

                    var formdata = new FormData();
                    formdata.append("file1", file);
                    formdata.append("fileName", fileName);
                    formdata.append("caseId", caseId);
                    formdata.append("fileType", fileType);


                    var ajax = new XMLHttpRequest();
                    ajax.upload.addEventListener("progress", progressHandler, false);
                    ajax.addEventListener("load", completeHandler, false);
                    ajax.addEventListener("error", errorHandler, false);
                    ajax.addEventListener("abort", abortHandler, false);
                    ajax.open("POST", "uploadCaseFile");
                    ajax.send(formdata);

                    
                }

                function progressHandler(event) {
                    _("loaded_n_total").innerHTML = "Uploaded " + event.loaded + " bytes of " + event.total;
                    var percent = (event.loaded / event.total) * 100;
                    _("progressBar").value = Math.round(percent);
                    _("status").innerHTML = Math.round(percent) + "% Uploaded... Please wait";
                }

                function completeHandler(event) {
                    _("status").innerHTML = event.target.responseText;
                    _("progressBar").value = 0; //wil clear progress bar after successful upload
                    
                    location.reload();
                    
                }

                function errorHandler(event) {
                    _("status").innerHTML = "Upload Failed";
                }

                function abortHandler(event) {
                    _("status").innerHTML = "Upload Aborted";
                }
            });
        </script>

        <!-- Modal -->
        <div class="modal fade" id="addFileModel" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" style="color: #337ab7;text-align: center">Add Case File </h4>
                    </div>
                    <div class="modal-body" style="color: #122B40">
                        <form class="form-horizontal" modelAttribute="CaseDocuments" action="#" enctype="multipart/form-data" method="POST">
                            <input type="hidden" value="${caseObj.caseId}" name="caseId" id="caseId">
                            <label class="col-md-3 control-label">File Name : </label>
                            <input type="text" class="form-control" style="width: 350px" name="fileName" id="fileName" required>
                            <br>

                            <label class="col-md-3 control-label">Choose File : </label>
                            <input class="form-control" style="width: 350px" type="file" name="file1" id="file1"  required> 
                            <br>

                            <label class="col-md-3 control-label">Choose File : </label>
                            <select class="form-control" name="fileType" id="fileType" style="width: 350px" required>
                                <option value="">Select</option>
                                <option value="Image">Image</option>
                                <option value="Documents">Documents</option>
                            </select>
                            <br>

                            <label class="col-md-3 control-label"> </label>
                            <input type="submit" class="btn btn-info" id="submitFormBtn"  value="ADD">
                        </form>

                        <br>
                        <progress  id="progressBar" value="0" max="100" style="width: 563px"></progress>
                        <h3 id="status" style="color: #337ab7;text-align: center"></h3>
                        <p id="loaded_n_total"style="color: #337ab7;text-align: center"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>






        <div class="row">
            <div class="mainview" >
                <h3 style="color: #122B40;text-align: center">Case Information</h3>
                <div class="panel panel-default">
                    <div class="panel-body" style="color: #122B40;background-color: #F0F0F0;">
                        <div class="col-md-4" style="border-right: 1px dashed #449D44;min-height: 400px"> 

                            <h4 style="color: #337ab7" class="col-md-4 control-label">Summary </h4>

                            <br>
                            <hr style="display: block;border-top: 1px solid #449D44;height: 1px;">

                            <p><b>Victim : </b>Age : ${caseObj.victimAge}</p>
                            <textarea style="width: 420px" readonly>${caseObj.victimName}</textarea>

                            <p>
                                <b>${caseObj.caseType}</b> committed on <b> ${caseObj.crimeDate}</b> and case filed on <b> ${caseObj.caseFileDate}</b>
                                at <b> ${caseObj.crimeLocation}</b> .
                            </p>
                            <p>
                                Investigating officer : <b> ${caseObj.investigatingOfficer}</b> 
                            </p>
                        </div>
                        <div class="col-md-4" style="border-right: 1px dashed #449D44;min-height: 400px">
                            <h4 style="color: #337ab7" class="col-md-4 control-label">Case Files </h4>
                            <button class="btn btn-info" data-toggle="modal" data-target="#addFileModel"  class="form-horizontal" style="float: right">Add</button>
                            <br>

                            <hr style="display: block;border-top: 1px solid #449D44;height: 1px;">

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Type</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${caseDocList}" var="cd">
                                    <tr>
                                        <td>${cd.fileName}</td>
                                        <td>${cd.fileType}</td>
                                        <td><a href="${cd.path}"><button>Download</button></a></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </div>
                        <div class="col-md-4" style="min-height: 400px">

                            <h4 style="color: #337ab7" class="col-md-3 control-label">Criminals</h4>
                            <button class="btn btn-info"  class="form-horizontal" style="float: right">Add</button>

                            <br>

                            <hr style="display: block;border-top: 1px solid #449D44;height: 1px;">

                            he problem with the above is that it the border sticks to my content in the first span. In the first span I have a form, and the border is hugging its contents. How can I separate it ? ? murtaza52 Jan 29 '13 at 11:53
                            Simple - add some padding to the content-left and content-right DIVS. ? Billy Moat Jan 29 '13 at 11:54
                            7
                            The problem would happen if the content of the right <span> higher than the left one. In that case, the vertical line will be ugly. ? lvarayut Jun 18 '14 at 11:43
                                If the columns are of different heights, you can make the line run all the way down by setting min-height: 100%; height: 100%; in the CSS for the container div and the divs containing each column. ? raul May 8 '16 at 2:52
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </div>    

</body>




</html>
