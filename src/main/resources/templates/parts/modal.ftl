<#macro modal>
    <form method="post" action="/login">
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Авторизация</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default-username">Username</span>
                            </div>
                            <input type="text" name="username" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default-username">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default-password">Password</span>
                            </div>
                            <input type="password" name="password" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default-password">
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-info" data-dismiss="modal" data-toggle="modal" data-target="#exampleModalCenterTwo">Регистрация</button>
                        <input type="submit" class="btn btn-success" value="Войти"/>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!-- Modal -->
    <form method="post" action="/registration">
        <#if message??>
            ${message}
        </#if>
        <div class="modal fade bd-example-modal-lg" id="exampleModalCenterTwo" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Регистрация</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Имя и Фамилия</span>
                            </div>
                            <input type="text" aria-label="First name" name="firstName" class="form-control">
                            <input type="text" aria-label="Last name" name="lastName" class="form-control">
                        </div>
                        <br>
                        <div class="input-group flex-nowrap">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="addon-wrapping">@</span>
                            </div>
                            <input type="text" class="form-control" name="username" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping">
                        </div>
                        <br>
                        <div class="input-group flex-nowrap">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="addon-wrapping">@</span>
                            </div>
                            <input type="email" class="form-control" name="email" placeholder="Email" aria-label="Username" aria-describedby="addon-wrapping">
                        </div>
                        <br>
                        <div class="input-group flex-nowrap">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="addon-wrapping">@</span>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="Password" aria-label="Password" aria-describedby="addon-wrapping">
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-success" value="Регистрация"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#macro>