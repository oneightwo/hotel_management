<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-8 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Просмотр пользователя</h5>
                    <div class="card-body">
                        <form id="userCreate" class="needs-validation" method="post">
                            <div class="card">
                                <div class="card-header">
                                    Системная информация
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="username">Имя пользователя</label>
                                        <input type="text" class="form-control" id="username" name="username"
                                               value="${user.username}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Описание</label>
                                        <textarea class="form-control" id="description" name="description" rows="3"
                                                  disabled>${user.description}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Роль</label>
                                        <select class="form-control" id="role" name="role" disabled>
                                            <#if user.role == "CONCIERGE">
                                                <option value="CONCIERGE" selected>Консьерж</option>
                                                <option value="MANAGER">Управляющий</option>
                                                <option value="OWNER">Владелец</option>
                                            </#if>
                                            <#if user.role == "MANAGER">
                                                <option value="CONCIERGE">Консьерж</option>
                                                <option value="MANAGER" selected>Управляющий</option>
                                                <option value="OWNER">Владелец</option>
                                            </#if>
                                            <#if user.role == "OWNER">
                                                <option value="CONCIERGE">Консьерж</option>
                                                <option value="MANAGER">Управляющий</option>
                                                <option value="OWNER" selected>Владелец</option>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-4">
                                <div class="card-header">
                                    Личная информация
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="surname">Фамилия</label>
                                        <input type="text" class="form-control" id="surname" name="surname"
                                               value="${user.surname}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Имя</label>
                                        <input type="text" class="form-control" id="name" name="name"
                                               value="${user.name}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="patronymic">Отчество</label>
                                        <input type="text" class="form-control" id="patronymic" name="patronymic"
                                               value="${user.patronymic}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateOfBirth">Дата рождения</label>
                                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                               value="${user.dateOfBirth}" disabled>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <a class="btn btn-warning" href="/users/edit/${user.id}">Изменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>