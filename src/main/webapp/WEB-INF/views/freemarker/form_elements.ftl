<!DOCTYPE html>
<html lang="en">
<#import "/spring.ftl" as spring />

<form class="form-narrow form-horizontal" method="post" action="<@spring.url '/freemarker/formelements'/>">
<@spring.bind "multiElementForm" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <br><br>
    Name: <@spring.formInput 'multiElementForm.name', 'class="form-control"' />
<@spring.showErrors "<br>" />

    <br><br>
    House Type: <@spring.formRadioButtons 'multiElementForm.houseType', houseTypes, '&nbsp;'/>
<@spring.showErrors "<br>" />

    <br><br> Iterate over a hash:<br>

    <ul>
    <#list houseColours?keys as value>
        <li>${value?html} - ${houseColours[value]?html}</li>
    </#list>
    </ul>


    Drop down created from a hash:
    <select id="houseSize" name="houseSize">
    <#list houseSizes?keys as value>
        <option value="${value?html}">${houseSizes[value]?html}</option>
    </#list>
    </select>

    <br><br>
    <button type="submit">Submit</button>
</form>


</html>
