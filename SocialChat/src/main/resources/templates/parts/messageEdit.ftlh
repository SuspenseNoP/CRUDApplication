<div>
    Message editor
    <div>
        <form method="post">
            <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                   value="<#if message??>${message.text}</#if>" name="text" placeholder="Enter your message" />
            <input type="text" class="form-control"
                   value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Tag" />
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
            <button type="submit" class="btn btn-primary ml-5">Save message</button>
        </form>
    </div>
</div>
