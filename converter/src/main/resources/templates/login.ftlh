<#import "partial/main.ftlh" as main />

<@main.renderWith title="converter" styles=["/css/login.css"]>
    <div class="alert alert-danger text-center" role="alert" style="margin-top: 20px; align-content: center">
        <div style="max-width: 300px;" class="text-left">
            <p>ADMIN : login: admin, password: admin </p> <p>USER: login: user, password: user</p>
        </div>
    </div>
    <div class="wrapper fadeInDown">
        <div id="formContent" class="border border-dark">
            <h2>Войти в конвертер валют</h2>
            <form action="/login" method="post">
                <div style="margin: 1rem">
                    <#if error!false >
                        <p style="color:#fd7777"><strong>Неверный логин или пароль!</strong></p>
                    </#if>
                </div>
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input type="text" id="login" class="fadeIn" name="username" placeholder="логин" required>
                <input type="password" id="password" class="fadeIn" name="password" placeholder="пароль" required>
                <input type="submit" class="fadeIn" value="Войти">
            </form>

        </div>
    </div>
</@main.renderWith>