title @p subtitle {"text":" "}
tp @e[type=villager,tag=rocket_villager,limit=2,sort=nearest] ~ ~-360 ~
title @p subtitle {"text":"Do not leave the rocket from now on.","color":"gold","bold":true}
fill ~5 ~5 ~5 ~-5 ~-5 ~-5 minecraft:fire replace minecraft:air
tag @s remove launchpad_good