<nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light light shadow fixed-top">
    <a class="navbar-brand" href="/rooms">Booking</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/rooms">Номера</a>
            </li>
            <#if authUser?? && authUser.role == "OWNER">
                <li class="nav-item">
                    <a class="nav-link" href="/users">Сотрудники</a>
                </li>
            </#if>
        </ul>

        <#if !(authUser??)>
            <a class="btn btn-success" href="/login">Войти</a>
        <#else>
            <#if authUser.role == "OWNER">
                <a class="btn btn-primary mr-1" href="/users/registration">Зарегистрировать</a>
            </#if>
            <#if authUser.role == "MANAGER" || authUser.role == "OWNER">
                <a class="btn btn-info mr-1" href="/rooms/reports">Создать отчет</a>
                <a class="btn btn-success mr-1" href="/rooms/create">Добавить комнату</a>
            </#if>
            <a class="btn btn-outline-danger mr-1" href="/logout">Выйти</a>
        </#if>
    </div>
</nav>
<div style='margin-top: 71px'></div>