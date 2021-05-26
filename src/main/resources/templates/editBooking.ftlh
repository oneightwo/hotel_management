<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Просмотр брони</h5>
                    <div class="card-body">
                        <form id="editBooking" method="post" class="needs-validation"
                              action="/booking/${booking.id}">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <input type="text" class="form-control" id="room.id" name="room.id"
                                           value="${booking.room.id}" hidden>
                                    <div class="form-group">
                                        <label for="name">Название</label>
                                        <input type="text" class="form-control" id="name" name="name"
                                               value="${booking.room.name}"
                                               required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Комната</label>
                                        <textarea class="form-control" id="description" name="description"
                                                  rows="6" required disabled>${booking.room.description}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="number">Номер</label>
                                        <input type="number" class="form-control" id="number" name="number"
                                               value="${booking.room.number}" required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="floor">Этаж</label>
                                        <input type="number" class="form-control" id="floor" name="floor"
                                               value="${booking.room.floor}"
                                               required disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="price">Цена за сутки</label>
                                        <input type="number" min="1" step="any" class="form-control" id="price"
                                               name="price"
                                               value="${booking.room.price?string.computer}" required disabled>
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
                                               value="${booking.surname}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Имя</label>
                                        <input type="text" class="form-control" id="name" name="name"
                                               value="${booking.name}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="patronymic">Отчество</label>
                                        <input type="text" class="form-control" id="patronymic" name="patronymic"
                                               value="${booking.patronymic}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateOfBirth">Дата рождения</label>
                                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                               value="${booking.dateOfBirth}" required>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-4">
                                <div class="card-header">
                                    Детали брони
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="price">Цена брони</label>
                                        <input type="number" min="1" step="any" class="form-control" id="price"
                                               name="price"
                                               value="${booking.totalPrice?string.computer}" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label for="fromDate">Бронь с</label>
                                        <input type="datetime-local" class="form-control" id="fromDate"
                                               name="fromDate"
                                               value="${booking.fromDate}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="endDate">Бронь до</label>
                                        <input type="datetime-local" class="form-control" id="endDate"
                                               name="endDate"
                                               value="${booking.endDate}" required>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="editBooking" type="submit" class="btn btn-success">Сохранить</button>
                        <a class="btn btn-warning" href="/rooms">Отменить</a>
                        <button class="btn btn-danger" onclick="deleteBooking(${booking.id})">Завершить бронь</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        deleteBooking = (id) => {
            sendRequest('DELETE', 'http://localhost:9123/booking/' + id)
                .then(response => {
                    window.location.replace("http://localhost:9123/rooms");
                });
        }
    </script>

</@c.page>