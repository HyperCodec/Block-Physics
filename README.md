# Block Physics
 
### Config
- maxaffectedblocks - The max amount of blocks changed by one block update (0 for infinite)
- chainupdates - Whether or not blocks update each other at all (useful against lag and with autoupdates)
- fallingblocksupdate - Whether or not the falling blocks landing can cause stuff to fall
- projectilesupdate - Whether or not projectiles can cause updates when they land
- autoupdatedistance - Distance from players that blocks autoupdate (0 to disable)
- realisticexplosions - Whether or not explosions have realistic qualities to them
- explosionupdates - Whether or not explosions update nearby blocks (excluding blocks flung from it) (requires realisticexplosions to be true)
- shiftignorephysics - Whether or not you can shift click to ignore block physics on the the block placed
- stableblocks - A list of materials that will ignore block physics
- unstableblocks - A list of materials that will not fall, but blocks above can fall if being supported by a member of it

### Permissions
- blockphysics.shiftclick - Whether or not the player is able to use shiftignorephysics (allowed by default)