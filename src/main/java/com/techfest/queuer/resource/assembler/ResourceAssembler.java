package com.techfest.queuer.resource.assembler;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<DomainType, ResourceType> {

	public abstract ResourceType toResource(DomainType domainObj);
	
	public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjs) {
		return domainObjs.stream().map(obj -> toResource(obj)).collect(Collectors.toList());
	}
}
