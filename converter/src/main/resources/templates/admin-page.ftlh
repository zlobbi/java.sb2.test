<#import "partial/main.ftlh" as main />
<@main.renderWith title="converter" scripts=[] styles=[]>

    <div class="alert alert-success text-center" id="hello" role="alert" style="margin-top: 20px; align-content: center">
        <div class="text-left">
            <p>Текуший урл для запроса: ${currentUrl}</p>
        </div>
    </div>
    <form action="/change-link" method="post" class="exit-form ml-auto">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                   value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <input name="newUrl" required/>
        <button type="submit" class="btn mx-auto border rounded btn-light">изменить</button>
    </form>

</@main.renderWith>