package app.viewex.app.details

import app.viewex.app.Identified
import app.viewex.app.IdentifiedPath
import app.viewex.app.Named

interface ObjectMetadata<
        Path : IdentifiedPath,
        Name : ObjectName,
        Details : ObjectDetails
        > : Identified<Path>, DetailsProvider<Details>, Named<Name>
