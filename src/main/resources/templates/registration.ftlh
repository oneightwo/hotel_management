<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class='col-8 p-0 m-0'>
                <div class="card">
                    <h5 class="card-header">Регистрация</h5>
                    <div class="card-body">
                        <form id="userCreate" class="needs-validation" method="post">
                            <div class="card">
                                <div class="card-header">
                                    Системная информация
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="username">Имя пользователя</label>
                                        <input type="text" class="form-control" id="username" name="username" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Пароль</label>
                                        <input type="password" class="form-control" id="password" name="password"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Описание</label>
                                        <textarea class="form-control" id="description" name="description" rows="3"
                                                  required></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="role">Роль</label>
                                        <select class="form-control" id="role" name="role">
                                            <option value="CONCIERGE" selected>Консьерж</option>
                                            <option value="MANAGER">Управляющий</option>
                                            <option value="OWNER">Владелец</option>
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
                                        <input type="text" class="form-control" id="surname" name="surname" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Имя</label>
                                        <input type="text" class="form-control" id="name" name="name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="patronymic">Отчество</label>
                                        <input type="text" class="form-control" id="patronymic" name="patronymic"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateOfBirth">Дата рождения</label>
                                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                               required>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="userCreate" type="submit" class="btn btn-success">Сохранить</button>
                        <a class="btn btn-danger" href="/users">Отменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>