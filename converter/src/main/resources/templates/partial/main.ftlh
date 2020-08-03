<#macro renderWith title="Abc-system!" scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

        <title>${title}</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
              integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
              crossorigin="anonymous"/>
        <link rel="stylesheet" href="//cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
              crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css" crossorigin="anonymous">

        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}"/>
        </#list>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="//cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
    </head>

    <body>

    <header class="header border-bottom border-dark">
        <div class="container">
            <#if user??>
                <div class="row">
                    <p class="" style="text-transform:uppercase;">Авторизован: ${user}</p>
                    <form action="/logout" method="post" class="exit-form ml-auto">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <button type="submit" class="btn mx-auto border rounded btn-light">Выйти</button>
                    </form>
                </div>
            </#if>
        </div>
    </header>

    <div class="container mt-3">

        <#nested>
    </div>
    <footer class="footer mt-5 py-1 border-top border-dark">
        <div class="container mt-3 text-right">
            <p class="">kadyrbek.mavlyanov@gmail.com</p>
        </div>
    </footer>
    </body>
    </html>
    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

</#macro>
