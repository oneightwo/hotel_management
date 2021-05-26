<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Редактирование</h5>
                    <div class="card-body">
                        <form id="roomEdit" method="post" class="needs-validation" action="/rooms/edit/${room.id}">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name" value="${room.name}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description"
                                          rows="6" required>${room.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="number">Номер</label>
                                <input type="number" class="form-control" id="number" name="number"
                                       value="${room.number}" required>
                            </div>
                            <div class="form-group">
                                <label for="floor">Этаж</label>
                                <input type="number" class="form-control" id="floor" name="floor" value="${room.floor}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                <input type="number" min="1" step="any" class="form-control" id="price" name="price"
                                       value="${room.price}" required>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="roomEdit" type="submit" class="btn btn-success">Сохранить</button>
                        <a href="/rooms" class="btn btn-warning">Отменить</a>
                        <button class="btn btn-danger" onclick="deleteRoom(${room.id})">Удалить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        deleteRoom = (id) => {
            sendRequest('DELETE', 'http://localhost:9123/rooms/' + id)
                .then(response => {
                    window.location.replace("http://localhost:9123/rooms");
                });
        }
    </script>

</@c.page>