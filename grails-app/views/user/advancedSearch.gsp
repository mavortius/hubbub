<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Advanced Search</title>
    <meta name="layout" content="main"/>
</head>
<body>
<formset>
    <legend>Advanced Search for Friends</legend>
    <table>
        <g:form action="advancedResults">
            <tr>
                <td>Name</td>
                <td><g:textField name="fullName" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><g:textField name="email" /></td>
            </tr>
            <tr>
                <td>Homepage</td>
                <td><g:textField name="homepage" /></td>
            </tr>
            <tr>
                <td>Query Type:</td>
                <td>
                    <g:radioGroup values="['and', 'or', 'not']" name="queryType"
                                  labels="['And', 'Or', 'Not']" value="and">
                        ${it.radio} ${it.label}
                    </g:radioGroup>
                </td>
            </tr>
            <tr>
                <td />
                <td>
                    <g:submitButton name="search" value="Search" />
                </td>
            </tr>
        </g:form>
    </table>
</formset>
</body>
</html>