<#import "partial/main.ftlh" as main />

<@main.renderWith title="converter" scripts=["/js/login.js"] styles=["/css/login.css"]>
    <div class="wrapper fadeInDown">
        <div id="formContent">
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
                <input type="text" id="login" class="fadeIn second" name="username" value="user" required>
                <input type="password" id="password" class="fadeIn third" name="password" value="user" placeholder="пароль" required>
                <input type="submit" class="fadeIn fourth" value="Войти">
            </form>

        </div>
    </div>
</@main.renderWith>