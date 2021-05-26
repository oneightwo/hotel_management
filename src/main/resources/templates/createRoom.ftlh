<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Создать</h5>
                    <div class="card-body">
                        <form id="roomCreate" method="post" class="needs-validation" action="/rooms">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group">
                                <label for="description">Описание</label>
                                <textarea class="form-control" id="description" name="description"
                                          rows="6" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="number">Номер</label>
                                <input type="number" class="form-control" id="number" name="number" required>
                            </div>
                            <div class="form-group">
                                <label for="floor">Этаж</label>
                                <input type="number" class="form-control" id="floor" name="floor" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                <input type="number" min="1" step="any" class="form-control" id="price" name="price" required>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <button form="roomCreate" type="submit" class="btn btn-success">Сохранить</button>
                        <a class="btn btn-danger" href="/rooms">Отменить</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>