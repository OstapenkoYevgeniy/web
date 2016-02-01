<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="pages" required="true" type="java.lang.Integer" %>
<%@ attribute name="offset" required="true" type="java.lang.Integer" %>
<%@ attribute name="prefix" required="true" type="java.lang.String" %>


<div class="pagination-holder black clearfix">
    <ul id="dark-pagination" class="pagination"></ul>
</div>
<script type="application/javascript">
    $('#dark-pagination').pagination({
        pages: ${pages},
        hrefTextPrefix: '${prefix}',
        currentPage: ${offset},
        edges: 3,
        displayedPages: 5
    });
</script>


