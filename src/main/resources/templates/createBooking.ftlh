<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Создать</h5>
                    <div class="card-body">
                        <form id="booking" method="post" class="needs-validation" action="/booking">
                            <div class="card mb-4">
                                <div class="card-header">
                                    Номер
                                </div>
                                <div class="card-body">
                                    <input type="text" class="form-control" id="room.id" name="room.id"
                                           value="${room.id}" hidden>
                                    <div class="form-group">
                                        <label for="name">Название</label>
                                        <input type="text" class="form-control" id="name" name="name"
                                               value="${room.name}"
                                               required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Комната</label>
                                        <textarea class="form-control" id="description" name="description"
                                                  rows="6" required disabled>${room.description}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="number">Номер</label>
                                        <input type="number" class="form-control" id="number" name="number"
                                               value="${room.number}" required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="floor">Этаж</label>
                                        <input type="number" class="form-control" id="floor" name="floor"
                                               value="${room.floor}"
                                               required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="price">Цена за сутки</label>
                                        <input type="number" min="1" step="any" class="form-control" id="price"
                                               name="price"
                                               value="${room.price?string.computer}" required disabled>
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
                            <div class="card mt-4">
                                <div class="card-header">
                                    Детали брони
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="fromDate">Бронь с</label>
                                        <input type="datetime-local" class="form-control" id="fromDate" name="fromDate"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label for="endDate">Бронь до</label>
                                        <input type="datetime-local" class="form-control" id="endDate" name="endDate"
                                               required>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="booking" type="submit" class="btn btn-success">Сохранить</button>
                        <a class="btn btn-danger" href="/rooms">Отменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>