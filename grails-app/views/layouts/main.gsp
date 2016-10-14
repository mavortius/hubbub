<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Hubbub &raquo;
        <g:layoutTitle default="Welcome"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>

<div>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                   <asset:image src="headerlogo.png" alt="hubbub logo"/>
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" >
                <ul class="nav navbar-nav">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div>
    <div id="bd"><!-- start body -->
      <g:layoutBody/>
    </div>  <!-- end body -->
    <div id="ft">
        <div id="footerText">Hubbub - Social Networking on Grails</div>
        Version <g:meta name="app.version"/>
        on Grails <g:meta name="app.grails.version"/>
    </div>
</div>
<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
<asset:javascript src="application.js"/>

</body>
</html>
