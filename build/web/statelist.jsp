<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<option value="">Select a State</option>
<c:forEach items="${StateList}" var="state">
    <option <c:if test="${state.getStateCode()==User.getStateCode()}">selected</c:if> value="${state.getStateCode()}">
        ${state.getStateName()}
    </option>
</c:forEach>
