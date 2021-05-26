<#import "containers/common.ftlh" as c>

<@c.page>
    <#include "component/navbar.ftlh">

    <#function locolizedRole user>
        <#if user.role == "OWNER">
            <#return "Владелец">
        </#if>
        <#if user.role == "MANAGER">
            <#return "Управляющий">
        </#if>
        <#if user.role == "CONCIERGE">
            <#return "Консьерж">
        </#if>
    </#function>

    <div class="container-fluid w-100 m-0 p-2">
        <div class="d-flex justify-content-center">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th scope="col">Имя пользователя</th>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Роль</th>
                </tr>
                </thead>
                <#list users as user>
                    <tbody>
                    <tr onclick="openUser(${user.id})">
                        <td>${user.username}</td>
                        <td>${user.surname}</td>
                        <td>${user.name}</td>
                        <td>${user.patronymic}</td>
                        <td>${locolizedRole(user)}</td>
                    </tr>
                    </tbody>
                </#list>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        openUser = (id) => {
            window.location.replace('http://localhost:9123/users/' + id);
        }
    </script>

</@c.page>