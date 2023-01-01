package app.viewex.domain.event

interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}
