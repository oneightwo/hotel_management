<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <div class="col-8 p-0 m-0">
                <div class="card">
                    <h5 class="card-header">Просмотр номера</h5>
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name" value="${room.name}"
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
                                <input type="number" class="form-control" id="floor" name="floor" value="${room.floor}"
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                <input type="number" min="1" step="any" class="form-control" id="price" name="price"
                                       value="${room.price?string.computer}" required disabled>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <#if authUser?? && (authUser.role == "OWNER" || authUser.role == "MANAGER")>
                            <a class="btn btn-warning" href="/rooms/edit/${room.id}">Изменить</a>
                        </#if>
                        <#if !(booking??)>
                            <a class="btn btn-success" href="/booking/rooms/${room.id}">Забронировать</a>
                        <#else>
                            <a class="btn btn-success" href="/booking/${booking.id}">Просмотр брони</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>