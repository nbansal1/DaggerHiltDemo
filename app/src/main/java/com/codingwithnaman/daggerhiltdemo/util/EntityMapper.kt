package com.codingwithnaman.daggerhiltdemo.util

interface EntityMapper <Entity, DomainModel> {
    fun mapFromEntry(entity: Entity) : DomainModel
    fun mapToEntry(domainModel: DomainModel) : Entity
}