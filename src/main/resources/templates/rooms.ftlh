<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">
    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th scope="col">Название</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Этаж</th>
                    <th scope="col">Номер</th>
                    <th scope="col">Цена за сутки</th>
                </tr>
                </thead>
                <#list rooms as room>
                    <tbody>
                    <#if room.isBooking>
                        <tr class="bg-danger" onclick="openRoom(${room.id})">
                            <td>${room.name}</td>
                            <td>${room.description}</td>
                            <td>${room.floor}</td>
                            <td>${room.number}</td>
                            <td>${room.price?string.computer}</td>
                        </tr>
                    <#else>
                        <tr onclick="openRoom(${room.id})">
                            <td>${room.name}</td>
                            <td>${room.description}</td>
                            <td>${room.floor}</td>
                            <td>${room.number}</td>
                            <td>${room.price?string.computer}</td>
                        </tr>
                    </#if>
                    </tbody>
                </#list>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        openRoom = (id) => {
            window.location.replace('http://localhost:9123/rooms/' + id);
        }
    </script>

</@c.page>