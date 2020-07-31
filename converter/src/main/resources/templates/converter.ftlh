<#import "partial/main.ftlh" as main />

<@main.renderWith title="converter" styles=["/css/login.css"]>

    <div class="row">
        <form class="mx-auto mt-3 pb-4 px-5 mb-5 border border-right-0 border-left-0 border-dark" action="/exchange"
              method="post"
        >
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="mx-auto pb-2 text-center">
                <h2 class="text-dark">Конвертер</h2>
            </div>
            <div class="form-group mx-auto">
                <#if day??>
                    <select name="fromValute" required style="max-width: 250px;">
                        <#list  day.valutes as v>
                            <#if fromVal?? && fromVal==v.id>
                                <option selected value="${v.id}">${v.charCode} (${v.name})</option>
                            <#else>
                                <option value="${v.id}">${v.charCode} (${v.name})</option>
                            </#if>
                        </#list>
                    </select>
                    <select name="toValute" required style="max-width: 250px;">
                        <#if day.valutes??>
                            <#list  day.valutes as v>
                                <#if toVal?? && toVal==v.id>
                                    <option selected value="${v.id}">${v.charCode} (${v.name})</option>
                                <#else>
                                    <option value="${v.id}">${v.charCode} (${v.name})</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                </#if>
            </div>
            <div class="form-group mx-auto justify-content-between row">
                <input name="initSum" style="width: 250px;" <#if init??>value="${init}"</#if> type="number" step="0.01"
                       required
                       placeholder="исходная сумма">
                <input name="resultSum" style="width: 250px;" <#if sum??>value="${sum}"</#if> disabled
                       placeholder="получаемая сумма">
            </div>
            <div class="form-group d-flex">
                <button type="submit" class="btn mx-auto btn-light text-dark border-dark mt-2"
                        style="width: 250px;">Конвертировать
                </button>
            </div>

        </form>
    </div>
    <div class="row">
        <form action="/filter" class="mx-auto" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input type="date" name="date">
            <#if day??>
                <select name="fromVal" style="max-width: 250px;">
                    <option disabled selected>Выберите валюту</option>
                    <#list  day.valutes as v>
                        <option value="${v.id}">${v.charCode} (${v.name})</option>
                    </#list>
                </select>
                <select name="toVal" style="max-width: 250px;">
                    <option disabled selected>Выберите валюту</option>
                    <#list  day.valutes as v>
                        <option value="${v.id}">${v.charCode} (${v.name})</option>
                    </#list>
                </select>
            </#if>
            <button type="submit">фильтровать</button>
        </form>
    </div>

    <div class="history-block mb-5">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Исходная валюта</th>
                <th scope="col">Целевая валюта</th>
                <th scope="col">Исходная сумма</th>
                <th scope="col">Полученная сумма</th>
                <th scope="col">Дата</th>
            </tr>
            </thead>
            <tbody>
            <#if filtered?? >
                <#list filtered as ex>
                    <tr class="exchange-details">
                        <td>${ex.fromValute}</td>
                        <td>${ex.toValute}</td>
                        <td>${ex.initSum}</td>
                        <td>${ex.resultSum?string["0.###"]}</td>
                        <td>${ex.date}</td>
                    </tr>
                </#list>
            <#else >
                <#if exchangeHistory ??>
                    <#list exchangeHistory as ex>
                        <tr class="exchange-details">
                            <td>${ex.fromValute}</td>
                            <td>${ex.toValute}</td>
                            <td>${ex.initSum}</td>
                            <td>${ex.resultSum?string["0.###"]}</td>
                            <td>${ex.date}</td>
                        </tr>
                    </#list>
                </#if>
            </#if>

            </tbody>
        </table>

    </div>

</@main.renderWith>